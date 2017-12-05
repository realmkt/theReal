package first.sample.theRealShop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TheRealShopToUplus2 {

	// -----------------------------------------------//
	// 더리얼샵 유플러스 결제 //
	// -----------------------------------------------//
	@SuppressWarnings("deprecation")
	public static String theRealToUPlus2(HttpServletRequest request, HttpServletResponse response, String json)
			throws IOException, org.json.simple.parser.ParseException {
		// TODO Auto-generated method stub
		String UplresData = null;
		int timeout = 30;

		try {
			HttpPost httpost = new HttpPost(
					new URI("https://relay.mainpay.co.kr/v1/api/payments/payment/payyo/cancel"));
			// HttpPost httpost = new HttpPost(new
			// URI("https://relay.mainpay.co.kr/v1/api/payments/payment/payyo/trans"));
			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
					.setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();
			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());

			System.out.println("@@@requestparamtest@@@@" + request.getParameter("mbrNo"));

			StringEntity stringEntity = new StringEntity(json, "UTF-8");
			httpost.setEntity(stringEntity);

			/* 응답데이터를 받기위한 서비스호출 */
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			UplresData = EntityUtils.toString(resEntity);
			System.out.println("■■유플러스 응답데이터■■==" + UplresData);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return UplresData;
	}

}
