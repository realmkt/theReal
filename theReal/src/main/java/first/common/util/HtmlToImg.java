package first.common.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class HtmlToImg {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String message = "<html>";
		message += "<div><table id='tg-YqJ3z' class='tg' style='undefined;table-layout: fixed; width: 555px;border-collapse:collapse;border-spacing:0;border-color:#aabcfe;'>";
		message += "<colgroup>";
		message += "<col style='width: 101px'>";
		message += "<col style='width: 177px'>";
		message += "<col style='width: 101px'>";
		message += "<col style='width: 176px'>";
		message += "</colgroup>";
		message += "  <tr>";
		message += "    <th style='font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;font-weight:bold;font-size:large;text-align:right;vertical-align:top;' class='tg-m3kb' colspan='4'>전자영수증</th>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top' class='tg-f9es'>카드종류</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'></td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>카드번호</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>9410-0900-****-7624</td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>유효기간</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'></td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>거래일시</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'></td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>품명</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky' colspan='3'></td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>결제방법</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>카드결제</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>금액</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'></td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>거래종류</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>신용 승인</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>세금</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>0</td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>대표자</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>김해성외1</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>봉사료</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>0</td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>승인번호</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>18222747</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>합계</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'></td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>가맹점명</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky' colspan='3'></td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>가맹점주소</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky' colspan='3'>서울 중구 퇴계로100</td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>결제대행사</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky' colspan='3'>(주)신세계페이먼츠</td>";
		message += "  </tr>";
		message += "  <tr>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>문의전화</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>1588-4349</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>서명</td>";
		message += "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>나병준</td>";
		message += "  </tr>";
		message += "</table>";
		message += "</div>";
		message += "</body>";
		message += "</html>";

		JLabel label = new JLabel(message);
		label.setSize(500, 500);

		BufferedImage image = new BufferedImage(label.getWidth(), label.getHeight(), BufferedImage.TYPE_INT_ARGB);

		{
			// paint the html to an image
			Graphics g = image.getGraphics();
			g.setColor(Color.BLACK);
			label.paint(g);
			g.dispose();
		}

		// get the byte array of the image (as jpeg)
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		// byte[] bytes = baos.toByteArray();
		// ImageIO.write(image, "png", new File("C:\\dev\\test.png"));

	}

}
