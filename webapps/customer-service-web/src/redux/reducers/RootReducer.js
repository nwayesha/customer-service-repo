import { combineReducers } from 'redux';
import LoadCountryReducer from './Common/LoadCountryReducer';
import LoadCityReducer from './Common/LoadCityReducer';
import LoadCustomerReducer from './customer/LoadCustomerReducer';

export default combineReducers(
  // This would produce the following state object
  {
    countryList: LoadCountryReducer,
    cityList: LoadCityReducer,
    customerList: LoadCustomerReducer

  });