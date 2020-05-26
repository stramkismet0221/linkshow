package com.ddzhuan.manage.tool.alipay;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
//	public static String appid = "2016051701410302";
//	
//	public static String open_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCkVpRlahXJXUYS8uL/B9mtUx3t1OFyj7HN/15fKYV01Q9ybF1gAya2nQHXwaN6qMp9c7GdNEY/KjefXglum4NyhSIzhWS4X9HBGYz6KEqHeSmpapAAx+LVrURgRZqeqe6ZBjEjCK646MGIlY55Rw1z2DzfOYrzL+/KO77GiBeOWCfBJGTvJgddOOJsYzdRV66yW7qp0LK5hjYTsD0/xQJ4CHiO54yQ6XsN4EOyYFCZbiymLTzrkutfVpibt7wX5FeCJB4zfs55ApyBGEqhsl5dfEDZ6zKcH7+xgsOAsldBoPbLUnf0PkIWwsLyUPuUObKkAVUd1i+5+toVDweY0hufAgMBAAECggEAIRPIJK14myIqA5BdviVPXAKZyJALnKptiF/5F8Bcu0pTJJ3JIZUgyqrJfyrbg8nLj/qkalbmOLp/nZUo4tlsWLWRT7turWmj53EEHinT7naluxLWz0FsV8PbOmFoIL1lZ+mGJF4QBOncYmhv19tESufgQLHtVMt2QUdLO9ySmTS0ghc3tGZF8K3qeOv7bcfvxplnr9DeYcMyH+CTfyssb98MSXoIjdAtDLVxYSU8Ts2zoGgIRgRBOl4krrKTchvIoiSrwI7K3OUV2sdV9ZIbKGvyN+WSFJJszRR1SnoaURRAOeAOiJFhlstnDdFS4HlTahX9jFn8xiyvoafdH0mbgQKBgQDYtXIsaXlcE45PWQzii1yRTpMJ9MlaiDzWagrvJijGPo96ZhI8H/ISKO4dRMUf8cY0lnXxzJSdWCnxyEiCcjRuQnnrsp9v5BjRUbx3FTALOQ9eUXQd+nOmr7ccj9kEDKGcZVFy70fAJlcR3BXjMmkPgV+WJz1sU6kFvYpxt0NTXwKBgQDCIlmDu32yeCY28+13pxPeF7ROo29c9FY+KhuLtA8YNDQw+glOMWnHY9v3S85vg5P4pDnt6FAo1PEDMA9U4bCOe5aZGsy2BF6hNb1uHAEi8axuU3LokxF5N8z28D1ahR5hF2s6JOOt+HmNKO6ZHSELF20QBTgm5akbdmeOgOBfwQKBgEndPrdIlCcAO2dsUOn+2mRkxdWE7ATBnwruRh/Rf+BifoEXvWtk8Buke9TaFd6XiBlwl279bkJbRKC2xZBA4QIcAnaGAbYfpx3hoD1uqVuJ7WUWml+FeFn+VomTtkKdE5C9MA/e4zPpXZAm1gcEra6tSClj4pnDwj7EpiWwc3A9AoGAJgmm8mnFKjfifPf0enJRMT4BxTq+sj7puwpzFT91ia+I3mPPePOzny7QxbajvzPPxXMgKp5PPoDirFY5Ws9R0ckja+JVZACdLgcik7WG/3rM510QccOHM/IcBfJygw78Mdft2ZmqxsadwmvXvEZ51/uCEhaDtxNevBPKHKDh1cECgYBtYJubMb9JYeEPYp/l/t4JPGJ/xijVEnHOD92TwusU4u4tadNU7Hcj2twmoh2XgMPXUOVbmQIsdeQrBK9AswdP1KGJAcTVteYKga+uxMGfS8bDXCrzIQlQImaAX4hwpK/SHFSg75EYm7tNjDQTzyfCcyHSbcGFGCJmmwDV2vCtpQ==";
//	
//	public static String open_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlqDpVozsBR0Fvq1POvRLBjuMewFwtHMstbrihgHE+hTfSIKQ038tvNmIc+L8jjxTV01f9apIVs+Wj5C08P3M9DCWO5Ufg4bFES8KklLeCLOWJn3TCavFBvcdNh5wcA1aM3oIYyPsr4UHwXAL0MP6rDSaGmB5hSoZ7kz6OEZ84xbi6+8GWX0xFnnyBlp6i/4s8MbFRF372hN5pP8kGzG1ST6kaP6JtN22lC2EN3SScub373Pco5mMGXA/KUPAy1rr5cDia1MpRoFt+x4BZbInyhzViOpxxuhGeSKyUM8wYBBSpzVL/6rVrtdJEAQGCQqQ2JBZ1BiaAPB+dTfA3xXiSwIDAQAB";
	
//	public static String open_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApFaUZWoVyV1GEvLi/wfZrVMd7dThco+xzf9eXymFdNUPcmxdYAMmtp0B18GjeqjKfXOxnTRGPyo3n14JbpuDcoUiM4VkuF/RwRmM+ihKh3kpqWqQAMfi1a1EYEWanqnumQYxIwiuuOjBiJWOeUcNc9g83zmK8y/vyju+xogXjlgnwSRk7yYHXTjibGM3UVeuslu6qdCyuYY2E7A9P8UCeAh4jueMkOl7DeBDsmBQmW4spi0865LrX1aYm7e8F+RXgiQeM37OeQKcgRhKobJeXXxA2esynB+/sYLDgLJXQaD2y1J39D5CFsLC8lD7lDmypAFVHdYvufraFQ8HmNIbnwIDAQAB";
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088221924053422";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = "2088221924053422";
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM2lqWoG82ddixseRA+Ei3kXTj1UOh9mp94bUFxVgqIhet729hhWOWGSncQ0MPm6kZ8M/U/9nls+KB20NafwlFJ8JmtWfPcpZL/kTLfDCGvCgHWUHYT9GduHh/jzZh/PJffCMxZeNIvN1irCecvFU2ZSwg6t5dX/CKNAIKrqjW+hAgMBAAECgYAwUsVEjO2VVH3jmXEU1GiRw9A2yRv/VPgNUQ3JJK7VulhNHGsnvNsu+mzoNftsM1MUtNCxgz864Zg7oqUZGYdAjs/kt7HTv9YdA86U5zlqVTdT9a02qFzpDffO1jRIROTkANkUM1yLva0KjVbsZdNZimy8GexlUy91AdY+jVZ0oQJBAO7v+ZoaRx6j+Ksy7rsDHcyQzZVQ1KSArtPYAT04nO1DWRSmYXsVbka6EMRSW+HfeaC18qwU1spG7uKauZQJWxcCQQDcVRsJAp0hVHI2YZYiP0LQAdscs7+MAUemgd/OmHVMUnJuS9ulRSof5hriVdIWQhCjlJQd3k7fTKw7Sp/B4t4HAkA3aNM7V2iyxFfAa9lM6RYaAGKMg+gsFeN1IRx4dA6APyQDU+o7PucJ49BLlsXUBZ0RFeCm41ZEJlrzlen/WIQVAkEAqUxHkrvMatDNVZXUW4pKVzQl3b8L/pL5MCr5AnDJKJTtUmy9YBduD8aqf6E75VhsGpVO3kh0SEZMzN5BNSSSPwJBAJsablrHpCPv2vIX8kyvTIuqgKaVCMifkmiBd/DySmDY/M7XmFXRC/jAeLr0NTWYY9pLB7m2wlJpXkt4sCD2SXk=";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXhZ+jVMr9Eov1THblWqjGMPtqEZ13hctKAy5vjGSTY4KdXiG1ZO5GG5dtWtxgasTsivt4oubr8rPd6w/iaIw/WpZt/G09kq0NFvNoX+g5EY0YkJQp927njqYEoQKegCCw9enKUFfoFosYpZ8NK+QlCRVkNWPeaSA+z8EN5t611QIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "C:\\alilog\\ddzali";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";
	
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串

	public static String udz_partner = "2088921409189511";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String udz_seller_id = "2088921409189511";
	// 商户的私钥                                                                          jfcr064hc6qks0729gbqz2z721kqzwk4
	public static String udz_private_key = "jfcr064hc6qks0729gbqz2z721kqzwk4";
	
	// 支付宝的公钥，无需修改该值
	public static String udz_ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXhZ+jVMr9Eov1THblWqjGMPtqEZ13hctKAy5vjGSTY4KdXiG1ZO5GG5dtWtxgasTsivt4oubr8rPd6w/iaIw/WpZt/G09kq0NFvNoX+g5EY0YkJQp927njqYEoQKegCCw9enKUFfoFosYpZ8NK+QlCRVkNWPeaSA+z8EN5t611QIDAQAB";
	                                            
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String udz_log_path = "C:\\alilog\\udzali";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String udz_input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String udz_sign_type = "MD5";


}
