// import React, { Component } from 'react';
// import {
//     Container, Table
// } from 'reactstrap';

// import Retriever from '../middleware/Retriever';

// render() 
      
//     return(

//       <Container>

//         <h5 className="text-center"> Number of Users:{' '+this.state.apiObject.length} </h5>
//         <div style={{ maxWidth:'1800px', maxHeight:'275px', overflow:'scroll'}}>
//           <Table bordered condensed>
//             <thead style={{background: '#599BE9',color:'white'}}>
//             <th className="text-center" >First Name</th>
//             <th className="text-center" >Last Name</th>
//             <th className="text-center" >isActive</th>
//             </thead>
//             <tbody style={{background:'#d3d3d3'}}>

//             {
//               this.state.apiObject.map(x =>
//                 <tr>
//                   <td>{x.firstName.toString()}</td>
//                   <td>{x.lastName.toString()}</td>
//                   <td>{x.isActive.toString()}</td>
//                 </tr>
//               )}

//             </tbody>
//           </Table>
//         </div>
//       </Container>
//   );




// export default Delivery;