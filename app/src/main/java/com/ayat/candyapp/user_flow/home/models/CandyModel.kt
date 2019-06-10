package com.ayat.candyapp.user_flow.home.models

import com.google.gson.annotations.SerializedName

data class CandyModel(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)