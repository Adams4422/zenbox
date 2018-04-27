import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';


var ordre;


class App extends Component {
  constructor(){
    super();
    ordre ="";
  }

clickedCommande(){

  this.setState({ordre :this.refs.ORDRE.value})
  ordre = this.refs.ORDRE.value;
  console.log(ordre);


  fetch('http://localhost:8080/rest/CoreNLP/Commande', {
    method: 'POST',

    headers: {
      'Access-Control-Allow-Origin': '*',
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
    },
    body: JSON.stringify({
      ordre : ordre,
    })
  })
}

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>


        <p className="Editordre">
        ORDRE : <input type="String" ref = "ORDRE" className = "ordre" />
        <button onClick ={(e) => {this.clickedCommande();}}>OK</button>
        </p>

        <p className="Commande">
        Commande envoyée : {ordre}
        </p>



      </div>
    );
  }
}


export default App;
