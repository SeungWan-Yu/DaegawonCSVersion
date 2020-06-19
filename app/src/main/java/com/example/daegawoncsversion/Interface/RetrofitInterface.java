package com.example.daegawoncsversion.Interface;


import com.example.daegawoncsversion.Models.Callmodels;
import com.example.daegawoncsversion.Models.Canclemodels;
import com.example.daegawoncsversion.Models.CountModels;
import com.example.daegawoncsversion.Models.Dbmodels;
import com.example.daegawoncsversion.Models.Delmodels;
import com.example.daegawoncsversion.Models.Fixedmodels;
import com.example.daegawoncsversion.Models.Kakaomodels;
import com.example.daegawoncsversion.Models.Loginmodels;
import com.example.daegawoncsversion.Models.Relistmodels;
import com.example.daegawoncsversion.Models.Reservemodels;
import com.example.daegawoncsversion.Models.Reserveupmodels;
import com.example.daegawoncsversion.Models.Seatschmodels;
import com.example.daegawoncsversion.Models.Tablemodels;
import com.example.daegawoncsversion.Models.Tlistmodels;
import com.example.daegawoncsversion.Object.CheckReobject;
import com.example.daegawoncsversion.Object.KakaoObject;
import com.example.daegawoncsversion.Object.Keyobject;
import com.example.daegawoncsversion.Object.Loginobject;
import com.example.daegawoncsversion.Object.RePhoneObject;
import com.example.daegawoncsversion.Object.ReserveObject;
import com.example.daegawoncsversion.Object.ReservechObject;
import com.example.daegawoncsversion.Object.Seatobject;
import com.example.daegawoncsversion.Object.Seatschobject;
import com.example.daegawoncsversion.Object.TRemoveobject;
import com.example.daegawoncsversion.Object.TableObject;
import com.example.daegawoncsversion.Object.TlistObject;
import com.example.daegawoncsversion.Object.TodayReobject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/addWait")
    Call<Kakaomodels> getkakaoJson(@Body KakaoObject kakaoObject); //Post방식의 Body는 따로 Object를 만듬

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/fixedBooking")
    Call<Fixedmodels> reservecheck(@Body CheckReobject checkReobject); //Post방식의 Body는 따로 Object를 만듬

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/deleteBooking")
    Call<Canclemodels> reservecancel(@Body CheckReobject checkReobject); //Post방식의 Body는 따로 Object를 만듬

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/login")
    Call<Loginmodels> login(@Body Loginobject loginobject); //Post방식의 Body는 따로 Object를 만듬

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/getWaitList")
    Call<Dbmodels> getDB(@Body Keyobject keyobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/deleteWait")
    Call<Delmodels> removeWait(@Body Seatobject seatobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/call")
    Call<Callmodels> callWait(@Body Seatobject seatobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/getWaitCnt")
    Call<CountModels> currentWait(@Body Keyobject keyobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/seat")
    Call<Tablemodels> seatrequest(@Body TableObject tableObject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/getSeatList")
    Call<Tablemodels> seatlist(@Body Keyobject keyobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/unseat")
    Call<Tablemodels> seatremove(@Body TRemoveobject tRemoveobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/addBooking")
    Call<Reservemodels> reserve(@Body ReserveObject reserveobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/getBookingSeatList")
    Call<Seatschmodels> seatsch(@Body Seatschobject seatschobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/updateBooking")
    Call<Reserveupmodels> reservech(@Body ReservechObject reservechobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/getBookingList")
    Call<Relistmodels> relist(@Body Keyobject keyobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/getBookingList")
    Call<Relistmodels> relist2(@Body TodayReobject todayReobject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/getBookingList")
    Call<Relistmodels> relist3(@Body RePhoneObject rePhoneObject);

    @Headers({"Accept: application/json","Content-Type:application/json;charset=UTF-8"})
    @POST("/api/getTlist")
    Call<List<Tlistmodels>> tlist(@Body TlistObject tlistObject);
}