import { useState } from 'react';
import Average from './components/Average';

const App = () => {
  const [visible, setVisible] = useState(true);
  return <Average />;
};

export default App;