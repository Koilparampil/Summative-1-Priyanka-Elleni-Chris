import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Console from "./pages/Console.jsx"
import Game from "./pages/Game.jsx"
import TShirt from "./pages/TShirt.jsx"
import Home from "./pages/Home.jsx"
import NavBar from './components/NavBar';
function App() {
  return (
<BrowserRouter>
<NavBar/>
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
