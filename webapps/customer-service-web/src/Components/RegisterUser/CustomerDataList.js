import React, { Component } from "react";
import { connect } from 'react-redux';
import BootstrapTable from 'react-bootstrap-table-next';
import { LoadCustomer } from '../../redux/actions/customer/LoadCustomerAction'

 //table details here
 const customerList = [
    { id: 1, name: "Ayesha", address:"Upplands Vasby", telephone: 111111, email: "nwayesha@gmail.com" },
    { id: 2, name: "Thusitha",address:"Upplands Vasby",telephone: 222222,email: "thusitha@gmail.com" },
    { id: 3, name: "Sandeli",address:"Upplands Vasby", telephone: 333333,email: "sandeli@gmail.com" },
    { id: 4, name: "Shaneli",address:"Upplands Vasby", telephone: 444444, email: "shaneli@gmail.com" },
    { id: 5, name: "Amindu",address:"Upplands Vasby", telephone: 555555, email: "amindu@gmail.com" }
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
      //alert("componentWillReceiveProps")
      if (nextProps.customerList != null && nextProps.customerList.customerList.length > 0) {        
        this.customCustomerList(nextProps.customerList.customerList);
      }
      
    }

    customCustomerList(customerList) {     
      this.setState({
        customerList: customerList
      })
    }
   
    loadCustomerList = () => {
      this.props.loadCustomerList();
    }

    
    render() {
      return (
        <div>
          <BootstrapTable 
          keyField='id' 
          data={ customerList } 
          columns={ columns } 
          />
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
