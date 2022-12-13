import Header from './Header';
import Chat from './Chat';
import Footer from './Footer';
import {Toaster} from 'react-hot-toast';

const App = () => {
    return (<>
        <Header/>
        <Chat/>
        <Footer/>
        <Toaster position="bottom-left"/>
    </>)
};

export default App;