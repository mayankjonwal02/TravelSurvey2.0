package com.jonwal.travel_survey_4.navigation

sealed class screens(var route : String)
{
    object splash : screens(route = "splash")
    object disclaimer : screens(route = "disclaimer")
    object onetimeform : screens(route = "onetimeform")
    object servicerunning : screens(route = "servicerunning")
    object startservice : screens(route = "start")
    object dayform : screens(route = "dayform")
    object thankyou : screens(route = "thankyou")
}
