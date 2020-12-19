package com.ktmmoe.shared.data

import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.utils.*

/**
 * Created by ktmmoe on 29, November, 2020
 **/
val specialitiesList = listOf(Speciality(name="သွားဖက်ဆိုင်ရာ", image = tooth),
    Speciality(name="ဆီးလမ်းကြောင်းအထူးကု",image =  kidney),
    Speciality(name="ဦးနှောက် နှင့် အာရုံကြောဆိုင်ရာ", image =  brain),
    Speciality(name="အရိုးအထူးကု", image = boneBroke),
    Speciality(name="နှလုံးအထူးကု",image =  heartDoctor),
    Speciality(name="အထွေထွေသမားတော်", image = generalDoctor),
    Speciality(name="အရေပြားဆိုင်ရာ အထူးကု",image =  skin),
    Speciality(name="သားဖွားမီးယပ်အထူးကု",image =  uterus),
    Speciality(name="သွားဖက်ဆိုင်ရာ", image = tooth),
    Speciality(name="ဆီးလမ်းကြောင်းအထူးကု",image =  kidney),
    Speciality(name="ဦးနှောက် နှင့် အာရုံကြောဆိုင်ရာ",image =  brain),
    Speciality(name="အရိုးအထူးကု",image =  boneBroke),
    Speciality(name="နှလုံးအထူးကု",image =  heartDoctor),
    Speciality(name="အထွေထွေသမားတော်",image =  generalDoctor),
    Speciality(name="အရေပြားဆိုင်ရာ အထူးကု",image =  skin),
    Speciality(name="သားဖွားမီးယပ်အထူးကု",image =  uterus)
)

val recentDoctorList = listOf(
    Doctor(name = "ပါမောက္ခဦးအောင်ဝင်း", specialtyId = "ဦးနှောက် နှင့် အာရုံကြောဆိုင်ရာ",image =  doctor1),
    Doctor(name = "ဒေါက်တာအောင်နိုင်စိုး", specialtyId =  "နှလုံးအထူးကု", image =  doctor2),
    Doctor(name = "ဒေါက်တာကြည်ကြည်ဝင်း", specialtyId = "အထွေထွေသမားတော်", image = doctor3)
)