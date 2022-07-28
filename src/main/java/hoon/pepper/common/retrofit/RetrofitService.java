package hoon.pepper.common.retrofit;

import com.google.common.collect.ImmutableMap;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.http.MediaType;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.time.Duration;
import java.util.Map;

public class RetrofitService {

	public static Builder Builder() {
		return new Builder();
	}

	public static final class Builder {
		private String domain;
		private Map<String, String> headers = ImmutableMap.of("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE, "Cache-Control", "no-cache");
		private Duration connectTimeout;
		private Duration readTimeout;
		private Duration writeTimeout;
		private Converter.Factory converter;
		private HttpLoggingInterceptor.Level loggingLevel = HttpLoggingInterceptor.Level.BODY;
		private boolean isUnsafe = false;

		public Builder(){}

		public Builder setDomain(String domain) {
			assert loggingLevel != null;
			this.domain = domain;
			return this;
		}

		public Builder setHeaders(Map<String, String> headers) {
			this.headers = headers;
			return this;
		}

		public Builder setConnectTimeout(Duration connectTimeout) {
			this.connectTimeout = connectTimeout;
			return this;
		}

		public Builder setReadTimeout(Duration readTimeout) {
			this.readTimeout = readTimeout;
			return this;
		}

		public Builder setWriteTimeout(Duration writeTimeout) {
			this.writeTimeout = writeTimeout;
			return this;
		}

		public Builder setConverter(Converter.Factory converter) {
			assert loggingLevel != null;
			this.converter = converter;
			return this;
		}

		public Builder setLoggingLevel(HttpLoggingInterceptor.Level loggingLevel) {
			assert loggingLevel != null;
			this.loggingLevel = loggingLevel;
			return this;
		}

		public Builder setIsUnsafe(boolean flag) {
			this.isUnsafe = flag;
			return this;
		}

		public <S> S build(Class<S> service) {
			Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(this.domain)
				.addConverterFactory(this.converter == null ? GsonConverterFactory.create() : this.converter)
				.client(isUnsafe ? buildUnsafeOkHttpClient() : buildOkHttpClient())
				.build();

			return retrofit.create(service);
		}

		private OkHttpClient buildOkHttpClient() {
			OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.addInterceptor(this.createInterceptor(headers))
				.addInterceptor(this.createLoggingInterceptor(loggingLevel));

			return setTimeout(builder).build();
		}

		private OkHttpClient buildUnsafeOkHttpClient() {
			try {
				final TrustManager[] trustAllCerts = new TrustManager[]{
					new X509TrustManager() {
						@Override
						public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
						}

						@Override
						public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
						}

						@Override
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return new java.security.cert.X509Certificate[]{};
						}
					}
				};

				final SSLContext sslContext = SSLContext.getInstance("SSL");
				sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

				final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

				OkHttpClient.Builder builder = new OkHttpClient.Builder()
					.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
					.hostnameVerifier((hostname, session) -> true)
					.addInterceptor(this.createInterceptor(this.headers))
					.addInterceptor(this.createLoggingInterceptor(this.loggingLevel));

				return setTimeout(builder).build();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		private OkHttpClient.Builder setTimeout(OkHttpClient.Builder builder) {
			if (this.connectTimeout != null) {
				builder.connectTimeout(this.connectTimeout);
			}

			if (this.readTimeout != null) {
				builder.readTimeout(this.readTimeout);
			}

			if (this.writeTimeout != null) {
				builder.writeTimeout(this.writeTimeout);
			}

			return builder;
		}

		private Interceptor createInterceptor(Map<String, String> headers) {
			return chain -> {
				Request.Builder requestBuilder = chain.request().newBuilder();
				for (String key : headers.keySet()) {
					requestBuilder.addHeader(key, headers.get(key));
				}
				return chain.proceed(requestBuilder.build());

			};
		}

		private Interceptor createLoggingInterceptor(HttpLoggingInterceptor.Level loggingLevel) {
			HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
			logging.setLevel(loggingLevel);
			return logging;
		}
	}
}

