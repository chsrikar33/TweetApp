import React from 'react'

import { useSearchParams,Link} from "react-router-dom";


const Dummy = () => {
 const[searchparams]=useSearchParams();
  const email=searchparams.get("id");
  return (
    <div>Dummy</div>
  )
}

export default Dummy