const LoadCustomerReducer = (state = {}, action) => {
    switch (action.type) {

        case 'CUSTOMER_FETCHING':
            return Object.assign({}, state, {
                fetching: true,
                customerList: {}
            });

        case 'CUSTOMER_RECEIVED':
            return Object.assign({}, state, {
                fetching: false,
                customerList: action.payload.data

            });

        case 'CUSTOMER_FAILED':
            return Object.assign({}, state, {
                fetching: false,
                customerList: {}
            });

        default:
            return state;
    }
}
export default LoadCustomerReducer;