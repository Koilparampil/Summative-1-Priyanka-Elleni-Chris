import React from 'react'
import "./Home.css"


const Home = () => {
  return (
    <>
<h1>Game Store</h1>

<div class="btn-group" >
  <button ><a href="/Game">Games</a></button>
  <button ><a href="/Console">Consoles</a></button>
  <button ><a href="/TShirt">T-Shirts</a></button>
</div>
</>
  )
}

export default Home