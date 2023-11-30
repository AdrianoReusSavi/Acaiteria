// dentro da pasta components

import React from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import ApiPage from './ApiPage';

const App: React.FC = () => {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/cashclosing-d/page">Fechamento</Link>
            </li>
            <li>
              <Link to="/api">API</Link>
            </li>
          </ul>
        </nav>

        <hr />

        <Route path="/cashclosing-d/page" exact component={() => <h1>Fechamento</h1>} />
        <Route path="/api" component={ApiPage} />
      </div>
    </Router>
  );
};

export default App;
