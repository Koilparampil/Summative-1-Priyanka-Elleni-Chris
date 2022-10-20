import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Console from "./pages/Console.jsx"
import Game from "./pages/Game.jsx"
import TShirt from "./pages/TShirt.jsx"
import Home from "./pages/Home.jsx"
function App() {
  return (
<BrowserRouter>
<Routes>
<Route path='/' element= {<Home/>}/>
<Route path='/Game' element= {<Game/>}/>
<Route path='/Console' element= {<Console/>}/>
<Route path='/TShirt' element= {<TShirt/>}/>
</Routes>
</BrowserRouter>
  );
}

export default App;
