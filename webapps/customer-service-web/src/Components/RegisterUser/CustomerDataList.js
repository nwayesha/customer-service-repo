import React, { Component } from "react";
import { connect } from 'react-redux';
import BootstrapTable from 'react-bootstrap-table-next';
import { LoadCustomer } from '../../redux/actions/customer/LoadCustomerAction'


 //table details here
 const products = [
    { id: 1, name: "Ordinarycoders course 1", price: 101 },
    { id: 2, name: "Ordinarycoders course 2", price: 102 },
    { id: 3, name: "Ordinarycoders course 3", price: 103 },
    { id: 4, name: "Ordinarycoders course 4", price: 104 },
    { id: 5, name: "Ordinarycoders course 5", price: 105 }
  ];
  const columns = [
    {
      dataField: "id",
      text: "User ID",
      sort: true
    },
    {
      dataField: "name",
      text: "User Name",
      sort: true
    },
    {
      dataField: "address",
      text: "Address"
    },
    {
      dataField: "telephone",
      text: "Telephone"
    },
    {
      dataField: "email",
      text: "Email"
    }
  ];

  class CustomerDataList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            date: new Date(),
            customerId :this.props.customerId,
            customerList: []
        };
       
       
    }   

    componentDidMount() {
      //alert("CustomerDataList-componentDidMount")	  
      this.loadCustomerList();
    
    }

    componentWillReceiveProps(nextProps) {
      alert("componentWillReceiveProps")
      if (nextProps.customerList != null && nextProps.customerList.customerList.length > 0) {
        //this.customCountryList(nextProps.countryList.countryList);

        alert("aaaaaaaaaaaaaaaaaaa");
      }
      
    }

    loadCustomerList = () => {
      this.props.loadCustomerList();
    }

    
    render() {
      return (
        <div>
          <BootstrapTable keyField='id' data={ products } columns={ columns } />
        </div>
    
      )
    }    
}

const mapStateToProps = state => {
  return {
    customerList: state.customerList
  }
}

const mapDipatchToProps = (dispatch) => ({
  loadCustomerList: () => dispatch(LoadCustomer())


});

export default connect(mapStateToProps, mapDipatchToProps)(CustomerDataList);
