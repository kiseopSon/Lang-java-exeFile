package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * IE 8.0인경우 화일다운로드 요청을 2회실시
 * (이유는 정확이 모르겠지만 화일다운로드 다이아로그가 띄어지면서 다시 호출하는것 같음.)
 * 첫번째 요청인경우 한글인코딩이 제대로 이뤄지는데
 * 두번째는 한글이 깨진다
 * 그래서 첫번째값만 저장해놓고 그값을 사용한다.
 */

@WebServlet("/FileDownload.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		String dir = request.getParameter("dir");
		//현재 프로젝트를 관리하는 객체
		//ServletContext application = request.getServletContext();
		//web상의 upload경로 -> 절대경로로 변환
		String fullpath = request.getServletContext().getRealPath(dir);
		String filename = "";
		filename = request.getParameter("filename");
		String fullpathname = String.format("%s/%s", fullpath,filename);
		//System.out.println(fullpathname);
		File file = new File(fullpathname);
		int max_size = 1024 * 1024 * 100;//최대업로드 용량이 100mb 알아서 제거됨 varchar2처럼
		byte [] b = new byte[1024*1024*4];
		
		 // 사용자 브라우저 타입 얻어오기
        String strAgent = request.getHeader("User-Agent");
        String userCharset = request.getCharacterEncoding();
        if(userCharset==null)userCharset="utf-8";

		//일반 파라미터와, 파일을 포함하고 있는 파라미터 처리를 위한 객체
		MultipartRequest mr = new MultipartRequest(request,path,max_size,"utf-8",new DefaultFileRenamePolicy());
		//위) 동일 파일명 정책 : 업로드하는 파일의 이름이 중복될 경우 알아서 넘버링을 해주기위한 클래스.
		//파일형식 이외의 파라미터를 수신하기 위해 이젠 그냥 request로 받으면 안됨 이미 일임 시켯기 떄문에 바로 오류
        
        //System.out.println("filename:"+filename+"\nagent:"+strAgent+"\ncharset:"+userCharset);
        //System.out.println("----------------------------------------------------------------");
        String value = "";
        // IE 일 경우
        if (strAgent.indexOf("MSIE") > -1) 
        {
            // IE 5.5 일 경우
            if (strAgent.indexOf("MSIE 5.5") > -1) 
            {
                value = "filename=" + filename ;
            }
            // 그밖에
            else if (strAgent.indexOf("MSIE 7.0") > -1) 
            {
                if ( userCharset.equalsIgnoreCase("UTF-8") ) 
                {
                	filename = URLEncoder.encode(filename,userCharset);
                	filename = filename.replaceAll("\\+", " ");
                    value = "attachment; filename=\"" + filename + "\"";

                }    
                else 
                {
                    value = "attachment; filename=" + new String(filename.getBytes(userCharset), "ISO-8859-1");
                   
                }
            }
            else{
            	//IE 8.0이상에서는 2회 호출됨..
            	if ( userCharset.equalsIgnoreCase("UTF-8") ) 
                {
                	filename = URLEncoder.encode(filename,"utf-8");
                	filename = filename.replaceAll("\\+", " ");
                    value = "attachment; filename=\"" + filename + "\"";
            		
                }    
                else 
                {
                    value = "attachment; filename=" + new String(filename.getBytes(userCharset), "ISO-8859-1");
                   
                }
            }
            
            
        }else if(strAgent.indexOf("Firefox") > -1){
        	//Firefox : 공백문자이후은 인식안됨...
        	value = "attachment; filename=" + new String(filename.getBytes(), "ISO-8859-1");
        }
       else {
            // IE 를 제외한 브라우저
            value = "attachment; filename=" + new String(filename.getBytes(), "ISO-8859-1");
        }
        
   
        response.setContentType("Pragma: no-cache"); 

		//실제로 업로드된 파일의 정보를 획득
		File f = mr.getFile("photo");
		if(f != null) {//업로드가 정상적으로 진행되었다면
			filename = f.getName();
		}
		//jsp에서 el표기법을 통해 값을 출력할 수 있도록 하기 위해  위에서 바인딩한 정보를 .jsp으로 포워딩

		String result = String.format("[{'result':'%s'},{'id':'%s'}]",res,id);
		
		//최종결과물인 result를 가지고 콜백메서드로 돌아간다.
		// response.getWriter().println(result);
		//다운로드는 reponse에 정보를 담아도 되고 안담아도 상관없다.
		//강제이동 : forward, redirect : 현재페이지로 재전송, return : ajax아니면 데이터는 전송되나, 화면에서 동기화처리 별도 해줘야함.
		// RequestDispatcher disp = request.getRequestDispatcher("result.jsp");
		// disp.forward(request, response);

		//전송 데이터가 stream 처리되도록 : 웹상전송 문자셋은 : 8859_1
		response.setContentType("application/octet-stream;charset=8859_1;");
		//모든 화일에 대하고 다운로드 대화상자가 열리게 설정
		//Content-Disposition : attachment
		 response.setHeader("Content-Disposition", value);
		//전송타입은 binary(이진화일)
		response.setHeader("Content-Transfer-Encoding", "binary;");
		if(file.isFile())
		{
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			int i=0;
			try
			{
				while((i=bis.read(b))!=-1)
				{
					bos.write(b,0,i);
				}
			}catch(Exception e){
				//e.printStackTrace();
			}finally {
				if(bos!=null)bos.close();
				if(bis!=null)bis.close();
				
			}
		}
	}
	
}
