import { BrowserRouter, Routes, Route } from 'react-router-dom';
   import Register from './pages/Register.jsx';
   import Login from './pages/Login.jsx';
   import Board from './pages/Board.jsx';

   export default function App() {
     return (
       <BrowserRouter>
         <Routes>
           <Route path="/" element={<Login />} />
           <Route path="/register" element={<Register />} />
           <Route path="/login" element={<Login />} />
           <Route path="/board" element={<Board />} />
         </Routes>
       </BrowserRouter>
     );
   }