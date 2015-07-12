package com.mongolia.website.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.util.NodeList;

import com.mongolia.website.model.DocumentValue;

public class DocAbstractUtil {
	public static void setdocview(DocumentValue documentValue)throws Exception {
		byte newcontent[] = null;
		if (documentValue.getDoccontent() != null) {
			newcontent = ungzipdoccontent(documentValue.getDoccontent());
			documentValue.setDoccontent(newcontent);
		}
		//
		if (documentValue.getDoctype().intValue() == StaticConstants.RESOURCE_TYPE_DOC) {
			String docContent = new String(documentValue.getDoccontent(),"GBK");
			// 替换flash 视频地址
			String matchStr = "\\[\\[http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?\\]\\]";
			Pattern destStri = Pattern.compile(matchStr);// ^
			Matcher mati = destStri.matcher(docContent);
			StringBuffer bufferi = new StringBuffer();
			while (mati.find()) {
				String groupi = mati.group(0);
				groupi = groupi.substring(2, groupi.length() - 2);
				String embed = "<br><embed pluginspage=\"http://www.macromedia.com/go/getflashplayer\"  src=\""
						+ groupi
						+ "\" allowFullScreen=\"true\" quality=\"high\" width=\"430\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>"
						+ "";

				// String embed =
				// "<iframe height=498 width=510 src=\"http://player.youku.com/embed/XODE0MDY0NzY4\" frameborder=0 allowfullscreen></iframe>";
				mati.appendReplacement(bufferi, embed);
			}
			mati.appendTail(bufferi);
			docContent = bufferi.toString();
			//
			matchStr = "\\[FLASH=http:\\//(player.ku6.com|player.youku.com|www.tudou.com|v.ifeng.com|you.video.sina.com.cn){1}(/\\w+[.[\\w|\\W]+]*)+(/(\\s)*\\w+.swf){1}\\]";
			destStri = Pattern.compile(matchStr);// ^
			mati = destStri.matcher(docContent);
			bufferi = new StringBuffer();
			while (mati.find()) {
				String groupi = mati.group(0);
				groupi = groupi.substring(1, groupi.length() - 1);
				String flashurl = groupi.split("=")[1];
				flashurl = flashurl.replaceAll(" ", "");
				String embed = "<br><embed pluginspage=\"http://www.macromedia.com/go/getflashplayer\"  src=\""
						+ flashurl
						+ "\" allowFullScreen=\"true\" quality=\"high\" width=\"430\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>";
				mati.appendReplacement(bufferi, embed);
			}
			mati.appendTail(bufferi);
			docContent = bufferi.toString();

			matchStr = "\\[FLASH=http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?\\]";
			destStri = Pattern.compile(matchStr);// ^
			mati = destStri.matcher(docContent);
			bufferi = new StringBuffer();
			while (mati.find()) {
				String groupi = mati.group(0);
				groupi = groupi.substring(1, groupi.length() - 1);
				String flashurl = groupi.split("=")[1];
				flashurl = flashurl.replaceAll(" ", "");
				String embed = "<embed pluginspage=\"http://www.macromedia.com/go/getflashplayer\"  src=\""
						+ flashurl
						+ "\" allowFullScreen=\"true\" quality=\"high\" width=\"430\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>";
				mati.appendReplacement(bufferi, embed);
			}
			mati.appendTail(bufferi);
			docContent = bufferi.toString();
			// 替换MP3地址
			matchStr = "\\{\\[[^\\)]+\\]\\}";
			destStri = Pattern.compile(matchStr);// ^
			mati = destStri.matcher(docContent);
			bufferi = new StringBuffer();
			while (mati.find()) {
				String groupi = mati.group(0);
				groupi = groupi.substring(2, groupi.length() - 2);
				String embed = "<br><embed pluginspage=\"http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash\" type=\"video/x-ms-wmv\"  src=\""
						+ groupi
						+ "\" controls=\"smallconsole\" loop=\"false\" autostart=\"true\" quality=\"high\" width=\"430\" height=\"400\" ></embed>"
						+ "";
				mati.appendReplacement(bufferi, embed);
			}
			mati.appendTail(bufferi);
			docContent = bufferi.toString();
			documentValue.setHtmlstr(docContent);
			Parser parser = Parser.createParser(documentValue.getHtmlstr(),
					"UTF-8");
			NodeFilter[] filters = new NodeFilter[1];
			filters[0] = new NodeClassFilter(ParagraphTag.class);
			NodeFilter filter = new OrFilter(filters);

			try {
				NodeList list = parser.extractAllNodesThatMatch(filter);
				String brhtml = "";
				int roundcount = 0;
				for (int i = 0; i < list.size(); i++) {
					NodeList childrenlist = list.elementAt(i).getChildren();
					if (childrenlist != null && childrenlist.size() > 5) {
						int substrposition = 0;
						for (int j = 0; j < childrenlist.size(); j++) {
							brhtml = brhtml
									+ childrenlist.elementAt(j).toHtml();
							String teststr = childrenlist.elementAt(j).toHtml()
									.replaceAll("&nbsp;", "");
							teststr = teststr.replaceAll("\r\n", "");
							teststr = teststr.replaceAll("<p>", "");
							teststr = teststr.replaceAll("</p>", "");
							if (!teststr.equalsIgnoreCase("")) {
								substrposition++;
								if (substrposition >= 10) {
									break;

								}
							}

						}
						// documentValue.setHtmlabc(brhtml);
						break;
					} else {
						brhtml = brhtml + list.elementAt(i).toHtml();
						String teststr = list.elementAt(i).toHtml()
								.replaceAll("&nbsp;", "");
						teststr = teststr.replaceAll("\r\n", "");
						teststr = teststr.replaceAll("<p>", "");
						teststr = teststr.replaceAll("</p>", "");
						if (!teststr.equalsIgnoreCase("")) {
							roundcount++;
							if (roundcount >= 10) {
								break;
							}
						}

					}
				}
				if (!brhtml.equalsIgnoreCase("")) {
					documentValue.setHtmlabc(brhtml);
				} else {
					int subindex=0;
					int roundcount1=0;
					while(roundcount1<15){
						int index=documentValue.getHtmlstr().indexOf("\r\n");
						if(index>subindex){
							subindex=index;
						}
						roundcount1++;
					}
					brhtml = documentValue.getHtmlstr().substring(0,
							subindex);
					documentValue.setHtmlabc(brhtml);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static byte[] ungzipdoccontent(byte[] contentn) {
		try {
			//
			ByteArrayInputStream in = new ByteArrayInputStream(contentn);
			GZIPInputStream ginzip = new GZIPInputStream(in);
			byte[] buffer = new byte[1024];
			int offset = -1;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			ginzip.close();
			out.flush();
			byte newcontent[] = out.toByteArray();
			out.close();
			in.close();
			return newcontent;
			//
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
