package nju.software.demo.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nju.software.demo.data.dataobject.Ljb;
import nju.software.demo.service.LjService;
import nju.software.demo.service.auth.AuthService;
import nju.software.demo.util.Base64Util;
import nju.software.demo.util.FileUtil;
import nju.software.demo.util.HttpUtil;
import nju.software.demo.util.JsonUtil;
import nju.software.demo.web.ResponseBuilder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminLjController {

	@Autowired
	private LjService ljService;
	@Autowired
	private ResponseBuilder responseBuilder;

	/**
	 * �����ӿ� ��ƽʱдControllerһ�� ����һ��@ResponseBody ���ҷ��ز��ǵ�ҳ����Ƿ���json��
	 * ��ַ:http://localhost:8080/demo/ljgl.do
	 */
	@RequestMapping(value = "/ljgl.do", method = RequestMethod.GET)
	@ResponseBody
	public String ljgl(HttpServletRequest request) {
		String ljlx = request.getParameter("ljlx");
		request.setAttribute("ljlx", ljlx);
		List<Ljb> ljList = ljService.getLjListByLjlx(ljlx);
		System.out.println(ljList);
		String json=null;
		JsonUtil jsonUtil= JsonUtil.getInstance();
		json=jsonUtil.toJson(ljList);
		try {
			json=Base64Util.getBASE64(json.getBytes("utf-8"));//base64���ܴ�
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}
	//���ýӿں�����ƽʱ������վ��һ���ģ�ֻ��������ѷ�����վ����������뻯
	//���뻯Ҳ��Ϊpost��get����
	@Test
	public void test() throws Exception{
		String requestUrl="https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
		String accessToken="";
		String params="";
		AuthService authService=new AuthService();
		String token=authService.getAuth();
		System.out.println(token);
		accessToken=token;
		byte[] b=FileUtil.readFileByBytes("D:/ss/timg.jpg");
		params=Base64Util.getBASE64(b);//һ�����֤����ͼƬ
		params = "id_card_side=back&" + URLEncoder.encode("image", "UTF-8") + "="
                + URLEncoder.encode(params, "UTF-8");//post����Ĳ���
		System.out.println(params);
		try {
			HttpUtil.post(requestUrl, accessToken, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
