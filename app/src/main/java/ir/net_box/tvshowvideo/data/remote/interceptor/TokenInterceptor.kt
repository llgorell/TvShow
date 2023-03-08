package ir.net_box.tvshowvideo.data.remote.interceptor

import ir.net_box.tvshowvideo.data.remote.TechnologyApi
import okhttp3.Interceptor
import javax.inject.Inject

class TokenInterceptor@Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request().newBuilder()
            .addHeader("SESSION-KEY", TechnologyApi.API_KEY)
            .build()
        return chain.proceed(request)
    }
}