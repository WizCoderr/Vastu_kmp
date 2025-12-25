package me.arun.vastu.features.home.courses.screens.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import me.arun.vastu.domain.model.PaymentRequest


import me.arun.vastu.domain.usecase.payment.CreatePaymentIntentUseCase


import me.arun.vastu.features.home.courses.screens.details.domain.usecase.GetDetailsDataUseCase


import kotlinx.coroutines.flow.*


import kotlinx.coroutines.launch





/**


 * Manages the business logic and state for the Details feature.


 */





class DetailsViewModel constructor(


    private val courseId: String,


    private val getDetailsDataUseCase: GetDetailsDataUseCase,


    private val createPaymentIntentUseCase: CreatePaymentIntentUseCase


) : ViewModel() {





    private val _state = MutableStateFlow(DetailsState())


    val state = _state.asStateFlow()





    private val _event = MutableSharedFlow<DetailsEvent>()


    val event = _event.asSharedFlow()





    init {


        loadInitialData()


    }





    fun onAction(action: DetailsAction) {


        when (action) {


            DetailsAction.OnEnrollClick -> {


                viewModelScope.launch {


                    createPaymentIntentUseCase(PaymentRequest(courseId))


                        .onSuccess {


                            // Handle success, e.g., navigate to payment screen


                            // For now, just log it


                            println("Payment intent created successfully")


                        }


                        .onFailure {


                            // Handle failure


                            println("Failed to create payment intent: ${it.message}")


                        }


                }


            }


        }


    }





    private fun loadInitialData() {


        viewModelScope.launch {


            _state.update { it.copy(isLoading = true, error = null) }





            getDetailsDataUseCase(courseId)


                .onSuccess { details ->


                    _state.update {


                        it.copy(


                            isLoading = false,


                            details = details


                        )


                    }


                }


                .onFailure { throwable ->


                    _state.update {


                        it.copy(


                            isLoading = false,


                            error = throwable.message ?: "Failed to load details"


                        )


                    }


                }


        }


    }


}