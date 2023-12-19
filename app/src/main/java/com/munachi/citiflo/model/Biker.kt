package com.munachi.citiflo.model

data class Biker(

    var PhotoUri: Int? = null,
    var deliveryType: String? = null,
    var deliveryProducts: String? = null,
    var bikerRoute: String? = null,
    var bikeName: String? = null,
    var bikeRating: String? = null,

)

data class BusOwner(

    var busPhotoUri: Int? = null,
    var busdeliveryType: String? = null,
    var busdeliveryProducts: String? = null,
    var busRoute: String? = null,
    var busName: String? = null,
    var busRating: String? = null,

    )

data class VanOwner(

    var vanPhotoUri: Int? = null,
    var vanDeliveryType: String? = null,
    var vanDeliveryProducts: String? = null,
    var vanRoute: String? = null,
    var vanName: String? = null,
    var vanRating: String? = null
)

data class TruckOwner(

    var truckPhotoUri: Int? = null,
    var truckDeliveryType: String? = null,
    var truckDeliveryProducts: String? = null,
    var truckRoute: String? = null,
    var truckName: String? = null,
    var truckRating: String? = null
)