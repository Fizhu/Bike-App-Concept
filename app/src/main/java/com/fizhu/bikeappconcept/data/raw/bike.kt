package com.fizhu.bikeappconcept.data.raw

import com.fizhu.bikeappconcept.App
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.data.models.Bike

/**
 * Created by fizhu on 10,July,2020
 * https://github.com/Fizhu
 */
object bike {
    private val imgRoadbike =
        "https://raw.githubusercontent.com/Fizhu/Bike-App-Concept/master/images/roadbike.png"
    private val imgMtb =
        "https://raw.githubusercontent.com/Fizhu/Bike-App-Concept/master/images/mtb.png"
    private val imgBmx =
        "https://raw.githubusercontent.com/Fizhu/Bike-App-Concept/master/images/bmx2.png"

    val listRoadBike: List<Bike> = arrayListOf(
        Bike(
            id = 0,
            name = "Felt FR2 FRD Ultimate",
            type = 0,
            image = imgRoadbike,
            desc = App.context?.getString(R.string.lorem),
            price = "$11,999.00"
        ),
        Bike(
            id = 1,
            name = "Felt FR2 FRD Ultimate",
            type = 0,
            image = imgRoadbike,
            desc = App.context?.getString(R.string.lorem),
            price = "$11,999.00"
        ),
        Bike(
            id = 2,
            name = "Felt FR2 FRD Ultimate",
            type = 0,
            image = imgRoadbike,
            desc = App.context?.getString(R.string.lorem),
            price = "$11,999.00"
        ),
        Bike(
            id = 3,
            name = "Felt FR2 FRD Ultimate",
            type = 0,
            image = imgRoadbike,
            desc = App.context?.getString(R.string.lorem),
            price = "$11,999.00"
        ),
        Bike(
            id = 4,
            name = "Felt FR2 FRD Ultimate",
            type = 0,
            image = imgRoadbike,
            desc = App.context?.getString(R.string.lorem),
            price = "$11,999.00"
        )
    )

    val listMtb: List<Bike> = arrayListOf(
        Bike(
            id = 5,
            name = "Trek Marlin 5",
            type = 1,
            image = imgMtb,
            desc = App.context?.getString(R.string.lorem),
            price = "$549.99"
        ),
        Bike(
            id = 6,
            name = "Trek Marlin 5",
            type = 1,
            image = imgMtb,
            desc = App.context?.getString(R.string.lorem),
            price = "$549.99"
        ),
        Bike(
            id = 7,
            name = "Trek Marlin 5",
            type = 1,
            image = imgMtb,
            desc = App.context?.getString(R.string.lorem),
            price = "$549.99"
        ),
        Bike(
            id = 8,
            name = "Trek Marlin 5",
            type = 1,
            image = imgMtb,
            desc = App.context?.getString(R.string.lorem),
            price = "$549.99"
        ),
        Bike(
            id = 9,
            name = "Trek Marlin 5",
            type = 1,
            image = imgMtb,
            desc = App.context?.getString(R.string.lorem),
            price = "$549.99"
        )
    )
    val listBmx: List<Bike> = arrayListOf(
        Bike(
            id = 10,
            name = "MirraCo Axium 2018",
            type = 2,
            image = imgBmx,
            desc = App.context?.getString(R.string.lorem),
            price = "$429.00"
        ),
        Bike(
            id = 11,
            name = "MirraCo Axium 2018",
            type = 2,
            image = imgBmx,
            desc = App.context?.getString(R.string.lorem),
            price = "$429.00"
        ),
        Bike(
            id = 12,
            name = "MirraCo Axium 2018",
            type = 2,
            image = imgBmx,
            desc = App.context?.getString(R.string.lorem),
            price = "$429.00"
        ),
        Bike(
            id = 13,
            name = "MirraCo Axium 2018",
            type = 2,
            image = imgBmx,
            desc = App.context?.getString(R.string.lorem),
            price = "$429.00"
        ),
        Bike(
            id = 14,
            name = "MirraCo Axium 2018",
            type = 2,
            image = imgBmx,
            desc = App.context?.getString(R.string.lorem),
            price = "$429.00"
        )
    )

}