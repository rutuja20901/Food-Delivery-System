package com.fooddelivery.userservice.dto;

public class UserResponseDto {
	 	private Long id;
	    private String fullName;
	    private String email;
	    private String mobileNumber;
	    private String role;
	    private String status;
	    
	    
		public UserResponseDto() {
			super();
		}


		public UserResponseDto(Long id, String fullName, String email, String mobileNumber, String role,
				String status) {
			super();
			this.id = id;
			this.fullName = fullName;
			this.email = email;
			this.mobileNumber = mobileNumber;
			this.role = role;
			this.status = status;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getFullName() {
			return fullName;
		}


		public void setFullName(String fullName) {
			this.fullName = fullName;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getMobileNumber() {
			return mobileNumber;
		}


		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}


		public String getRole() {
			return role;
		}


		public void setRole(String role) {
			this.role = role;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}
		
		
	    
	    
}
