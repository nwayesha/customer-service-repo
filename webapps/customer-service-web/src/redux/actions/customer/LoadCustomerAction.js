import axios from 'axios';
import { CONTROLLER_URL } from '../settings';


export const LoadCustomer = () => {

  let url = CONTROLLER_URL+'customer/customerDetails';
  //let url = "http://localhost:8080/customer/customerDetails";
  return (dispatch, getState) => {

    dispatch({ type: 'CUSTOMER_FETCHING', payload: { fetching: true, data: {} } });

    axios(encodeURI(`${url}`), {
      'headers': {
        'Content-Type': 'application/json'
        //'apiKey': getState ().apiKey.apiKey
      }
    }).then(response => {
      dispatch({ type: 'CUSTOMER_RECEIVED', payload: { fetchingData: false, data: response.data } });

    }).catch(e => {
      dispatch({ type: 'CUSTOMER_FAILED', payload: { fetchingData: false, data: e } });
    });
  }
}
