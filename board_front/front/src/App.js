import { Route, Routes, useNavigate } from 'react-router';
import './App.css';
import Auth from './components/Auth/Auth';
import Board from './components/Board/Board';
import Home from './pages/Home/Home';
import RootLayout from './components/RootLayout/RootLayout';
import { useQuery } from 'react-query';
import { authToken } from './apis/api/account';


function App() {
  const navigate = useNavigate();

  const getPrincipal = useQuery(["getPrincipal"], async () => {
    try {
      const response = await authToken();
      return response;
    } catch (error) {
      throw new Error(error);
    }
  }, {
    retry: 0,
    refetchInterval: 1000 * 60 * 10,
    refetchOnWindowFocus: false
  }
  );

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
