package shipping

type ScheduleOrderRequest struct {
	Id              string
	ShippingAddress string
}

type GetOrderShippingInfoResponse struct {
	Id              string
	ShippingAddress string
	Status          string
}
