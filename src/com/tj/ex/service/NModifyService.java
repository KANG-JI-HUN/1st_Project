package com.tj.ex.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.FreeboardDao;
import com.tj.ex.dao.NboardDao;

public class NModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("filebordUp");
		int maxSize = 1024 * 102 * 10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String nFilename = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			nFilename = mRequest.getFilesystemName(param);
			String dbFileName = mRequest.getParameter("dbFileName");
			if (nFilename == null) {
				nFilename = dbFileName;
			}
			// nNo, nSubject, nContent, nFilename, nPw, nDate
			int nNo = Integer.parseInt(mRequest.getParameter("nNo"));
			String nSubject = mRequest.getParameter("nSubject");
			String nContent = mRequest.getParameter("nContent");
			String nPw = mRequest.getParameter("nPw");
			NboardDao fDao = new NboardDao();
			int result = fDao.modify(nNo, nSubject, nContent, nFilename, nPw);
			// joinCustomer 결과에 따라 적절히 request.setAttribute
			if (result == FreeboardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "공지사항 글수정 성공");
			} else {
				request.setAttribute("resultMsg", "공지사항 글수정 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String pageNum = mRequest.getParameter("pageNum");
			request.setAttribute("pageNum", pageNum);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "공지사항 글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path + "/" + nFilename);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_IT/source/6_JSP/ch20_project/WebContent/fileboardUp/" + nFilename);
				byte[] bs = new byte[(int) serverFile.length()];
				while (true) {
					int nByteCnt = is.read(bs);
					if (nByteCnt == -1)
						break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (os != null) os.close();
					if (is != null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
