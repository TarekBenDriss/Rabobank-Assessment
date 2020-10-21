package com.bendrisstarek.rabobankassessment.modules.main

import android.util.Log
import java.io.Serializable

class StandardModel(list: java.util.ArrayList<String>?) : Serializable {
     var list: java.util.ArrayList<String> = list!!

     fun listToString(): String {
          var result = ""
          for(s in list)
               result = s
          return result
     }


     override fun toString(): String {
          var result = ""
          for(s in list)
               result = s

          Log.e("resultTag",result)
          return result
     }


}