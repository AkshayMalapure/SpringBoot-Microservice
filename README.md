# SpringBoot-Microservice


Application Overview
	There are two services interacting with each other. Services are User Service And Department Service.
	Client can create Department details using Department service.
	Department service has two endpoints post to create department details.
	Get Endpoint to view details of Department.
	Client can create User details  using Department service. User service has two endpoints post to create User details
	.While creating the user details client needs to enter department id.
	Get Endpoint to view details of User. This Get Endpoint internally calls department service to fetch department details based on id.
	Final Response returned from Get Endpoint of User will have user details along with department.



