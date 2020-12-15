package com.lu.utils.http;

import com.alibaba.fastjson.JSONObject;
import com.lu.utils.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 通用http发送方法
 *
 * @author lzkj
 */
public class HttpUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private static final int READ_TIMEOUT = 30 * 1000; // 半分钟

    private static final int CONNECT_TIMEOUT = 30 * 1000; // 半分钟

    private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8"; // 参数类型json

    private static final String CONTENT_TYPE_XML = "application/xml; charset=utf-8"; // 参数类型xml

    private static final String ACCEPT_DEFAULT = "*/*"; // 默认响应数据类型
    private static final String ACCEPT_XML = "application/xml"; // 响应数据类型为xml

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 响应结果
     */
    public static String sendGet(String url, String param) {
        return sendGet(url, param, CONTENT_TYPE_JSON);
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url      发送请求的 URL
     * @param paramMap 请求参数
     * @return 响应结果
     */
    public static String sendGet(String url, Map<String, Object> paramMap) {
        return sendGet(url, mapParamToString(paramMap));
    }

    /**
     * 向指定 URL 发送GET方法的请求 xml参数
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，xml格式
     * @return 响应结果
     */
    public static String sendXmlGet(String url, String param) {
        return sendGet(url, param, CONTENT_TYPE_XML);
    }

    /**
     * 向指定 URL 发送GET方法的请求 xml参数
     *
     * @param url   发送请求的 URL
     * @param param 请求参数
     * @return 响应结果
     */
    public static String sendXmlGet(String url, Object param) {
        return sendGet(url, XmlUtil.beanToXml(param));
    }

    /**
     * 向指定 URL 发送POST请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 响应结果
     */
    public static String sendPost(String url, String param) {
        return sendPost(url, param, CONTENT_TYPE_JSON, null);
    }

    /**
     * 向指定 URL 发送POST请求
     *
     * @param url      发送请求的 URL
     * @param paramMap 请求参数，map形式。
     * @return 响应结果
     */
    public static String sendPost(String url, Map<String, Object> paramMap) {
        return sendPost(url, mapParamToString(paramMap));
    }

    /**
     * 向指定 URL 发送POST请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数 xml格式
     * @return 响应结果
     */
    public static String sendXmlPost(String url, String param) {
        return sendPost(url, param, CONTENT_TYPE_XML, null);
    }

    /**
     * 向指定 URL 发送POST请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数
     * @return 响应结果
     */
    public static String sendXmlPost(String url, Object param) {
        return sendXmlPost(url, XmlUtil.beanToXml(param));
    }

    /**
     * 向指定 URL 发送POST请求
     * 请求参数会被转成 json
     * @param headerMap 添加请求头，比如添加token
     * @param url       发送请求的 URL
     * @param param     请求参数
     * @return 响应结果
     */
    public static String sendPostAndHeader(Map<String, String> headerMap, String url, Object param) {
        return sendPost(headerMap, url, XmlUtil.beanToXml(param), CONTENT_TYPE_JSON, null);
    }

    /**
     * 向指定 URL 发送POST请求
     * 请求参数会被转成 xml
     * @param headerMap 添加请求头，比如添加token
     * @param url       发送请求的 URL
     * @param param     请求参数
     * @return 响应结果
     */
    public static String sendXmlPostAndHeader(Map<String, String> headerMap, String url, Object param) {
        return sendPost(headerMap, url, XmlUtil.beanToXml(param), CONTENT_TYPE_XML, null);
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url         发送请求的 URL
     * @param param       请求参数
     * @param contentType 参数类型 json或者xml
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String contentType) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            log.info("sendGet - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("GET");
            // 设置读取超时时间
            conn.setReadTimeout(READ_TIMEOUT);
            // 设置连接超时时间
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Content-Type", contentType == null ? CONTENT_TYPE_JSON : contentType);
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.connect();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("sendGet返回 - {}", result);
        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, String contentType, String acceptType) {
        return sendPost(null, url, param, contentType, acceptType);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * header 添加请求头
     *
     * @param url   发送请求的 URL
     * @param param 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(Map<String, String> headerMap, String url, String param, String contentType, String acceptType) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            String urlNameString = url;
            log.info("sendPost - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("POST");
            // 设置是否向HttpURLConnection输出，因为这个是post请求，参数要放在http正文内，需要设为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置读取超时时间
            conn.setReadTimeout(READ_TIMEOUT);
            // 设置连接超时时间
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setRequestProperty("Content-Type", contentType == null ? CONTENT_TYPE_JSON : contentType); // 参数内容格式
            conn.setRequestProperty("Accept", acceptType == null ? ACCEPT_DEFAULT : acceptType); // 响应body格式
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if (headerMap != null) {
                Set set = headerMap.keySet();
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    String key = iter.next().toString();
                    if (headerMap.get(key) != null) {
                        // 扩展header
                        conn.setRequestProperty(key, headerMap.get(key));
                    }
                }
            }

            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("sendPost返回 - {}", result);
        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    public static String sendSSLPost(String url, String param, String contentType) {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try {
            log.info("sendSSLPost - {}", urlNameString);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestMethod("POST");
            // 设置是否向HttpURLConnection输出，因为这个是post请求，参数要放在http正文内，需要设为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置读取超时时间
            conn.setReadTimeout(READ_TIMEOUT);
            // 设置连接超时时间
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("Accept-Charset", "utf-8");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while ((ret = br.readLine()) != null) {
                if (ret != null && !"".equals(ret.trim())) {
                    result.append(new String(ret.getBytes("ISO-8859-1"), "utf-8"));
                }
            }
            log.info("recv - {}", result);
            conn.disconnect();
            br.close();
        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
        }
        return result.toString();
    }

    /**
     * 获取客户端ip地址
     */
    public static String getClientIp(HttpServletRequest request) {
        // 网宿cdn的真实ip
        String ip = request.getHeader("Cdn-Src-Ip");
        if (StringUtils.isBlank(ip) || " unknown".equalsIgnoreCase(ip)) {
            // 蓝讯cdn的真实ip
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip) || " unknown".equalsIgnoreCase(ip)) {
            // 获取代理ip
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(ip) || " unknown".equalsIgnoreCase(ip)) {
            // 获取代理ip
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            // 获取代理ip
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            // 获取真实ip
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 根据IP获取地址
     */
    public static JSONObject getIpAddress(String ip) {
        if (ip == null || ip.trim().length() < 1) {
            return null;
        }
        String code = "0";
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
        try {
            String res = sendGet(url, "");
            JSONObject json = JSONObject.parseObject(res);
            String backCode = json.getString("code");
            if (code.equals(backCode)) {
                return json.getJSONObject("data");
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    static String mapParamToString(Map<String, Object> paramsMap) {
        String param = "";
        if (paramsMap != null && !paramsMap.isEmpty()) {
            StringBuffer str = new StringBuffer();
            Set set = paramsMap.keySet();
            Iterator iter = set.iterator();
            while (iter.hasNext()) {
                String key = iter.next().toString();
                if (paramsMap.get(key) == null) {
                    continue;
                }
                str.append(key).append("=").append(paramsMap.get(key)).append("&");
            }
            if (str.length() > 0) {
                param = str.substring(0, str.length() - 1);
            }
        }
        return param;
    }

    /**
     * 当方法为空是默认为所有的链接都为安全,
     * 也就是所有的链接都能够访问到.
     * 当然这样有一定的安全风险,可以根据实际需要写入内容.
     */
    private static class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}