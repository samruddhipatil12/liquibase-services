package org.dnyanyog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional; // Correct import for Optional
import org.dnyanyog.common.ResponseCodes;
import org.dnyanyog.dto.CustomerRequest;
import org.dnyanyog.dto.CustomerResponse;
import org.dnyanyog.dto.SearchCustomerResponse;
import org.dnyanyog.entity.Customer;
import org.dnyanyog.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// @Component
public class CustomerServiceImpl implements CustomerService {

  @Autowired CustomerRepository customerRepo;

  @Autowired CustomerResponse customerResponse;

  @Autowired SearchCustomerResponse searchCustomerResponse;

  @Override
  public CustomerResponse addCustomerDetails(CustomerRequest customerRequest) {

    if (customerRepo.existsByEmail_id(customerRequest.getEmailID())) {
      customerResponse.setStatus(ResponseCodes.Add_Customer_Duplicate_Email_Code);
      customerResponse.setMessage(ResponseCodes.Add_Customer_Duplicate_Email_message);
      customerResponse.setCustomerCode(0000);
    } else {

      Customer customerTable =
          Customer.getInstance()
              .setFirst_name(customerRequest.getFirstName())
              .setMiddle_name(customerRequest.getMiddleName())
              .setLast_name(customerRequest.getLastName())
              .setDate_of_birth(customerRequest.getDateOfBirth())
              .setAddress_line1(customerRequest.getAddressLine1())
              .setAddress_line2(customerRequest.getAddressLine2())
              .setZip(customerRequest.getZip())
              .setCity(customerRequest.getCity())
              .setState(customerRequest.getState())
              .setCountry(customerRequest.getCountry())
              .setMobile_phone(customerRequest.getMobilePhone())
              .setHome_phone(customerRequest.getHomePhone())
              .setWork_phone(customerRequest.getWorkPhone())
              .setEmail_id(customerRequest.getEmailID())
              .setCustomer_id(customerRequest.getCustomerId())
              .setCreated_date(LocalDateTime.now())
              .setUpdated_date(LocalDateTime.now());

      try {
        customerTable = customerRepo.save(customerTable);
      } catch (Exception e) {
        e.printStackTrace();
      }

      customerResponse.setStatus("Success");
      customerResponse.setMessage("Customer added successfully!!");
      customerResponse.setCustomerCode(customerTable.getCustomer_code());
    }
    return customerResponse;
  }

  public CustomerResponse updateCustomerDetails(long id, CustomerRequest customerRequest) {

    Optional<Customer> optionalCustomer = customerRepo.findById(id);
    if (optionalCustomer.isPresent()) {
      Customer customer = optionalCustomer.get();
      // Update the existing customer object with new details
      customer.setFirst_name(customerRequest.getFirstName());
      customer.setMiddle_name(customerRequest.getMiddleName());
      customer.setLast_name(customerRequest.getLastName());
      customer.setDate_of_birth(customerRequest.getDateOfBirth());
      customer.setAddress_line1(customerRequest.getAddressLine1());
      customer.setAddress_line2(customerRequest.getAddressLine2());
      customer.setZip(customerRequest.getZip());
      customer.setCity(customerRequest.getCity());
      customer.setState(customerRequest.getState());
      customer.setCountry(customerRequest.getCountry());
      customer.setMobile_phone(customerRequest.getMobilePhone());
      customer.setHome_phone(customerRequest.getHomePhone());
      customer.setWork_phone(customerRequest.getWorkPhone());
      customer.setEmail_id(customerRequest.getEmailID());
      customer.setUpdated_date(LocalDateTime.now());

      try {
        // Save the updated customer object
        customer = customerRepo.save(customer);
      } catch (Exception e) {
        e.printStackTrace();
      }

      customerResponse.setStatus("Success");
      customerResponse.setMessage("Customer details updated successfully!!");
      customerResponse.setCustomerCode(customer.getCustomer_code());
    } else {
      customerResponse.setStatus("Fail");
      customerResponse.setMessage("Customer not present");
      customerResponse.setCustomerCode(0000);
    }

    return customerResponse;
  }

  public SearchCustomerResponse findByMobileNumber(String mobile_number) {

    List<Customer> customerList = customerRepo.findByMobile(mobile_number);
    if (customerList.isEmpty()) {
      searchCustomerResponse.setStatus("Fail");
      searchCustomerResponse.setMessage("Customer not found !!");
      searchCustomerResponse.setCustomerCode(0000);
    } else {
      Customer receivedData = customerList.get(0);

      searchCustomerResponse.setStatus("Success");
      searchCustomerResponse.setMessage("Customer details are as follows :");
      searchCustomerResponse.setCustomerCode(receivedData.getCustomer_code());
      searchCustomerResponse.getCustomerData().setFirstName(receivedData.getFirst_name());
      searchCustomerResponse.getCustomerData().setMiddleName(receivedData.getMiddle_name());
      searchCustomerResponse.getCustomerData().setLastName(receivedData.getLast_name());
      searchCustomerResponse.getCustomerData().setDateOfBirth(receivedData.getDate_of_birth());
      searchCustomerResponse.getCustomerData().setAddressLine1(receivedData.getAddress_line1());
      searchCustomerResponse.getCustomerData().setAddressLine2(receivedData.getAddress_line2());
      searchCustomerResponse.getCustomerData().setZip(receivedData.getZip());
      searchCustomerResponse.getCustomerData().setCity(receivedData.getCity());
      searchCustomerResponse.getCustomerData().setState(receivedData.getState());
      searchCustomerResponse.getCustomerData().setCountry(receivedData.getCountry());
      searchCustomerResponse.getCustomerData().setMobilePhone(receivedData.getMobile_phone());
      searchCustomerResponse.getCustomerData().setHomePhone(receivedData.getHome_phone());
      searchCustomerResponse.getCustomerData().setWorkPhone(receivedData.getWork_phone());
      searchCustomerResponse.getCustomerData().setEmailID(receivedData.getEmail_id());
    }

    return searchCustomerResponse;
  }
}
