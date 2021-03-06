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
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.OafterDao;

public class OWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("fileboardUp");
		int maxSize = 1024 * 1024 * 10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String oFilename = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			oFilename = mRequest.getFilesystemName(param);
			// mId, fTitle, fContent, fileName, fIp
			HttpSession httpSession = request.getSession();
			String cId = (String) httpSession.getAttribute("cId");
			int iNo = Integer.parseInt(mRequest.getParameter("iNo"));
			String oSubject = mRequest.getParameter("oSubject");
			String oContent = mRequest.getParameter("oContent");
			String oPw = mRequest.getParameter("oPw");
			OafterDao oDao = new OafterDao();
			int result = oDao.write(cId, iNo, oSubject, oContent, oFilename, oPw);
			// joinMember결과에 따라 적절히 request.setAttribute
			if (result == OafterDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "후기 글쓰기 성공");
			} else {
				request.setAttribute("resultMsg", "후기 글쓰기 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "후기 글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path + "/" + oFilename);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_IT/source/6_JSP/model2ex/WebContent/fileboardUp/" + oFilename);
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
