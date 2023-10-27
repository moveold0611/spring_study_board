import { Route, Routes } from 'react-router';
import './App.css';
import Home from './pages/Home/Home';
import RootLayout from './components/RootLayout/RootLayout';
import { useQuery } from 'react-query';
import { connentPrincipal } from './apis/api/principal';
import AuthRoute from './components/Routes/AuthRoute';
import BoardRoute from './components/Routes/BoardRoute';
import AccountRoute from './components/Routes/AccountRoute';
import StorePageRoute from './components/Routes/StorePageRoute';

function App() {

  const getPrincipal = useQuery(["getPrincipal"], async () => {
    try {
      const response = await connentPrincipal();
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

  if(getPrincipal.isLoading) {
    return <>로딩</>
  }

  return (
    <>
      <RootLayout>
        <Routes>        
          <Route path='/' element={<Home />}/>
          <Route path='auth/*' element={<AuthRoute />}/>
          <Route path='board/*' element={<BoardRoute />}/>
          <Route path='account/*' element={<AccountRoute />} />
          <Route path='store/*' element={<StorePageRoute/>}/>
        </Routes>
      </RootLayout>
    </>
  );
}

export default App;
