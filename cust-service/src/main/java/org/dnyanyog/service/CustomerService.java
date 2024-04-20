package org.dnyanyog.service;

import org.dnyanyog.dto.CustomerRequest;
import org.dnyanyog.dto.CustomerResponse;
import org.dnyanyog.dto.SearchCustomerResponse;

public interface CustomerService {

  public CustomerResponse addCustomerDetails(CustomerRequest customerRequest);

  public CustomerResponse updateCustomerDetails(long id, CustomerRequest customerRequest);

  // public CustomerResponse findByEmailId(String email_id);

  public SearchCustomerResponse findByMobileNumber(String mobile_number);
}
