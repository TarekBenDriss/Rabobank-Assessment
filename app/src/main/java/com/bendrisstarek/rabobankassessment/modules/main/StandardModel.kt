package com.bendrisstarek.rabobankassessment.modules.main

import java.io.Serializable

class StandardModel(list: java.util.ArrayList<String>?) : Serializable {
     var list: java.util.ArrayList<String> = list!!


     override fun toString(): String {
          var result = ""
          for(s in list)
               result = s
          return result
     }


}