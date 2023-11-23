package com.bbb.koha.network


import com.bbb.koha.common.Constant
import com.bbb.koha.dashboard.TripSheetResponse
import com.bbb.koha.login.RegisterResponseModel
import com.bbb.koha.login.model.UserDetailResponseModel
import com.bbb.koha.login.model.ValidateUserRequestModel
import com.bbb.koha.login.model.ValidateUserResponseModel
import com.bbb.koha.module.registration.model.AllLibraryResponseModel
import com.bbb.koha.module.splash_screen.TokenRequest
import com.bbb.koha.module.splash_screen.TokenResponse
import com.bbb.koha.tracking.MobileTrackResponseModel
import retrofit2.Response
import retrofit2.http.*

interface Apis {

    @POST("mMobileRegister.htm")
    suspend fun register(@QueryMap body: Map<String, String>): Response<List<RegisterResponseModel>>

    @GET("api/Tripsheet/DocketCartonListByTripsheet")
    suspend fun pickupRequest(@QueryMap body: Map<String, String>): TripSheetResponse

    @POST("mMobileTrack.htm")
    suspend fun trackUser(@QueryMap loginMap: Map<String, String>): Response<List<MobileTrackResponseModel>>

    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getToken(@Field("grant_type")  grantType: String = Constant.GRANT_TYPE,
                         @Field("client_id")  clientId: String = Constant.CLIENT_ID,
                         @Field("client_secret")  clientSecret: String = Constant.CLIENT_SECRET): TokenResponse

    @POST("auth/password/validation")
    suspend fun validateUser(@Body validateUserRequestModel: ValidateUserRequestModel): Response<ValidateUserResponseModel>

    @GET("patrons/{patronId}")
    suspend fun getUserDetail(@Path("patronId") patronId: Int?): Response<UserDetailResponseModel>

    @GET("libraries")
    suspend fun getLibraries(): Response<List<AllLibraryResponseModel>>

    /*@POST("signup")
    suspend fun signup(@Body signupRequestModel: SignupRequestModel): Response<SignUpResponseModel>

    @POST("verify-otp")n
    suspend fun verifyOTP(@Body otpVerificationRequestModel: OTPVerificationRequestModel): Response<OTPVerificationResponseModel>

    @POST("login")
    suspend fun login(@Body request: LoginRequestModel): Response<LoginResponseModel>

    @POST("forgot-password")
    suspend fun forgotPassword(@Body request: ForgotRequestModel): Response<ForgotResponseModel>

    @POST("reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequestModel): Response<ResetPasswordResponseModel>

    @POST("resend-otp")
    fun resendOTP(@Body request: ResendOTPRequestModel): Response<ResendOTPResponseModel>

    @GET("")
    suspend fun getHomePageData(): Response<DashboardResponseModel>*/
}