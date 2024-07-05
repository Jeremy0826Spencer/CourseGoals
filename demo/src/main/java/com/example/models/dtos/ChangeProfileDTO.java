package com.example.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ChangeProfileDTO {

        @NotBlank(message = "Must enter a first name.")
        private String firstName;
        @NotBlank(message = "Must enter a last name.")
        private String lastName;
        @NotBlank(message = "Must enter an email.")
        @Email(message = "Email must be valid.")
        private String email;

        public ChangeProfileDTO() {
        }

        public ChangeProfileDTO(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "RegisterDTO{" +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

}

