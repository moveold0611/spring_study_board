import { Route, Routes } from 'react-router';
import './App.css';
import Auth from './components/Auth/Auth';
import Board from './components/Board/Board';
import Home from './pages/Home/Home';
import RootLayout from './components/RootLayout/RootLayout';

function App() {
  return (
    <>
      <RootLayout>
        <Routes>        
          <Route path='/' element={<Home />}/>
          <Route path='/auth/*' element={<Auth />}/>
          <Route path='/board/*' element={<Board />}/>
        </Routes>
      </RootLayout>
    </>
  );
}

export default App;
