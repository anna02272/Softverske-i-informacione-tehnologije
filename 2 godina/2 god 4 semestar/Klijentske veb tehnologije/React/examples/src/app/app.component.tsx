import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import { Subtract } from 'utility-types';

export default function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul style={{columnCount: 4}}>
            <li>
              <Link to="/primer1">Primer1 - jednostavan React čvor</Link>
            </li>
            <li>
              <Link to="/primer2">Primer2 - složeni React čvor</Link>
            </li>
            <li>
              <Link to="/primer3">Primer3 - props React čvora</Link>
            </li>
            <li>
              <Link to="/primer4">Primer4 - inline CSS React čvora</Link>
            </li>
            <li>
              <Link to="/primer5">Primer5 - reakcije na događaje React čvora</Link>
            </li>
            <li>
              <Link to="/primer6">Primer6 - JSX</Link>
            </li>
            <li>
              <Link to="/primer7">Primer7 - JSX i JavaScript</Link>
            </li>
            <li>
              <Link to="/primer8">Primer8 - JSX i JavaScript - lista</Link>
            </li>
            <li>
              <Link to="/primer9">Primer9 - JSX i inline CSS</Link>
            </li>
            <li>
              <Link to="/primer10">Primer10 - JSX i obrada događaja</Link>
            </li>
            <li>
              <Link to="/primer11">Primer11 - Ugnježdene komponente</Link>
            </li>
            <li>
              <Link to="/primer12">Primer12 - Prenos podataka u komponente - props</Link>
            </li>
            <li>
              <Link to="/primer13">Primer13 - Klasne komponente</Link>
            </li>
            <li>
              <Link to="/primer14">Primer14 - Stanje komponente</Link>
            </li>
            <li>
              <Link to="/primer15">Primer15 - Životni ciklus komponente</Link>
            </li>
            <li>
              <Link to="/primer16">Primer16 - Forme - input element</Link>
            </li>
            <li>
              <Link to="/primer17">Primer17 - Forme - select element</Link>
            </li>
            <li>
              <Link to="/primer18">Primer18 - Forme - više input elemenata</Link>
            </li>
            <li>
              <Link to="/primer19">Primer19 - Komponenta višeg reda</Link>
            </li>
          </ul>
        </nav>

        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
        <Switch>
          <Route path="/primer1">
            <Primer1 />
          </Route>
          <Route path="/primer2">
            <Primer2 />
          </Route>
          <Route path="/primer3">
            <Primer3 />
          </Route>
          <Route path="/primer4">
            <Primer4 />
          </Route>
          <Route path="/primer5">
            <Primer5 />
          </Route>
          <Route path="/primer6">
            <Primer6 />
          </Route>
          <Route path="/primer7">
            <Primer7 />
          </Route>
          <Route path="/primer8">
            <Primer8 />
          </Route>
          <Route path="/primer9">
            <Primer9 />
          </Route>
          <Route path="/primer10">
            <Primer10 />
          </Route>
          <Route path="/primer11">
            <Primer11 />
          </Route>
          <Route path="/primer12">
            <Primer12 naslov="Biblioteke"/>
          </Route>
          <Route path="/primer13">
            <Primer13 naslov="Biblioteke - klase"/>
          </Route>
          <Route path="/primer14">
            <Primer14 />
          </Route>
          <Route path="/primer15">
            <Primer15 />
          </Route>
          <Route path="/primer16">
            <Primer16 />
          </Route>
          <Route path="/primer17">
            <Primer17 />
          </Route>
          <Route path="/primer18">
            <Primer18 />
          </Route>
          <Route path="/primer19">
            <Primer19 minValue={3} maxValue={8} />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}


// kreiranje React cvora
function Primer1() {
    var div1 = React.createElement('div', {id:'id1'}, 'Teksta u Primeru 1');
    return div1;
}

// slozeni React cvor
function Primer2() {
    var li1 = React.createElement('li',null,'React')
    var li2 = React.createElement('li',null,'Angular')
    var li3 = React.createElement('li',null,'Vue')
    var ul1 = React.createElement('ul',{id:'ul1'},li1,li2,li3)
    return ul1;
}

// props
function Primer3() {
    var div1 = React.createElement('div',{id:'div1', foo:'bar'},'Tekst u primeru 3');
    console.log('div1.props.id:',div1.props.id);
    console.log('div1.props.foo:',div1.props.foo);
    return div1;
}

// inline css u node čvorovima
function Primer4() {
    var inlineStyles = {
        color:'red',
        fontSize:22
    }
    var div1 = React.createElement('div',{style:inlineStyles},'Tekst u primeru 4');
    return div1;
}

// reakcija na događaje u React čvoru
function Primer5() {
  var divClickHandler = function() {
    console.log('kliknuto je na div');
  };
  var divMouseOverHandler = function() {
    console.log('prelazi se preko diva');
  };
  var div1 = React.createElement('div',{onClick:divClickHandler, onMouseOver:divMouseOverHandler}, 'Tekst u primeru 5');
  return div1;
}

// JSX
function Primer6() {
  var ul1 = <ul id="ul1">
    <li>React</li>
    <li>Angular</li>
    <li>Vue</li>
  </ul>
  return ul1;
}

// JSX i JavaScript
function Primer7() {
  var idCounter = 3;
  var div1 = <div id={'id_'+idCounter}>
    Ovo je tekst primera {4+3}. 
  </div>
  return div1;
}

// JSX i JavaScript
function Primer8() {
  const libraries = [{id:1,name:"React"},{id:2,name:"Angular"},{id:3,name:"Vue"}];
  var lis = libraries.map(x => <li>{x.name}</li>);
  var ul1 = <ul>{lis}</ul>;
  return ul1;
}

// JSX i inline CSS
function Primer9() {
  var styles = {color:"red",
                fontSize:20};
  var div1 = <div style={styles}>Tekst u primeru 9</div>
  return div1;
}

// JSX i događaji
function Primer10() {
  var divClickHandler = function() {
    console.log('kliknuto je na div');
  };
  var divMouseOverHandler = function() {
    console.log('prelazi se preko diva');
  };
  var div1 = <div onClick={divClickHandler} onMouseOver={divMouseOverHandler}>Tekst u primeru 10</div>
  return div1;
}

function Naslov() {
  return <h1>Ugnježdene komponente</h1>
}

function Primer11() {
  return <div>
    <Naslov></Naslov>
    <div>Ovo je tekst u primeru 11</div>
  </div>
}

function Biblioteka(props:{id:number,name:string}) {
  return <li>id:{props.id}, naziv:{props.name}</li>;
}

function Biblioteke(props:{libraries:{id:number,name:string}[]}){
  return <div>
    <ul>
      {props.libraries.map(x => <Biblioteka id={x.id} name={x.name}></Biblioteka>)}
    </ul>
  </div>
}

function Primer12(props:{naslov:string}) {
  const libraries = [{id:1,name:"React"},{id:2,name:"Angular"},{id:3,name:"Vue"}];
  // props = {naslov:'Bla'};
  return <div>
    <h1>{props.naslov}</h1>
    <Biblioteke libraries={libraries}></Biblioteke>
  </div>
}

class BibliotekaC extends React.Component<{id:number,name:string},{}> {
  render(){
    return <li>id:{this.props.id}, naziv:{this.props.name}</li>;
  }
}

class BibliotekeC extends React.Component<{libraries:{id:number,name:string}[]},{}>{
  render(){
    return <div>
      <ul>
        {this.props.libraries.map(x => <BibliotekaC id={x.id} name={x.name}></BibliotekaC>)}
      </ul>
    </div>
  }
}

class Primer13 extends React.Component<{naslov:string},{}>{
  render() {
    const libraries = [{id:1,name:"React"},{id:2,name:"Angular"},{id:3,name:"Vue"}];
    return <div>
      <h1>{this.props.naslov}</h1>
      <BibliotekeC libraries={libraries}></BibliotekeC>
    </div>
    }
}

class Primer14 extends React.Component<{},{counter:number, title:string}> {
  constructor(props:{}){
    super(props);
    this.state = {counter:1, title:"Stanje"};
  }

  render() {
    const uvecajBrojac = () => {this.setState({counter:this.state.counter+1})};
    return <div>
      <h1>{this.state.title}</h1>
      brojac: {this.state.counter}
      <button onClick={uvecajBrojac}>uvecaj</button>
    </div>
  }
}

// zivotni ciklus komponente
class Primer15 extends React.Component<{},{counter:number, title:string}> {
  constructor(props:{}){
    super(props);
    this.state = {counter:1, title:"Stanje"};
  }

  componentDidMount() {
    console.log('Componente did mount!')
  }

  componentDidUpdate(prevProps: {}, prevState:{counter:number, title:string}) {
    console.log('Componente did update!')
  }

  componentWillUnmount() {
    console.log('Componente will unmount!')
  }

  shouldComponentUpdate(nextProps:{}, nextState:{counter:number, title:string}) {
    console.log('Should componente update!')
    if(nextState.counter<=5){
      return true;
    }
    else{
      return false;
    }
  }

  static getDerivedStateFromProps(props:{}, state:{counter:number, title:string}) {
    console.log('Derivede state from props!')
    console.log(state);
    return state;
  }

  getSnapshotBeforeUpdate(prevProps: {}, prevState:{counter:number, title:string}) {
    console.log('Snapshot before update!');
    console.log(prevState);
    return prevState;
  }

  render() {
    const uvecajBrojac = () => {this.setState({counter:this.state.counter+1})};
    return <div>
      <h1>{this.state.title}</h1>
      brojac: {this.state.counter}
      <button onClick={uvecajBrojac}>uvecaj</button>
    </div>
  }
}

// Kontrolisana komponente (forma)
class Primer16 extends React.Component<{},{name:string, submitted:string}> {

  constructor(props:{}) {
    super(props);
    this.state = {name:'',submitted:''}
  }

  handleChange = (event: React.ChangeEvent<HTMLInputElement>)=>{
    this.setState({name:event.target.value});
  }

  handleSubmit = (event: React.FormEvent<HTMLFormElement>)=>{
    event.preventDefault();
    this.setState({submitted:this.state.name});
  }

  render() {
    return <div>
    <form className="form-inline" onSubmit={this.handleSubmit}>
      <div className="form-group">
        <label htmlFor="ime">ime</label>
        <input type="text" className="form-control" id="ime" placeholder="ime" value={this.state.name} onChange={this.handleChange}></input>
      </div>
      <button type="submit" className="btn btn-primary">posalji</button>
    </form>
    <h2>submitted: {this.state.submitted}</h2>
    </div>
  }

}

// forme - select polje
class Primer17 extends React.Component<{},{value:string, submitted:string}>{
  
  constructor(props:{}) {
    super(props);
    this.state = {value:'react',submitted:''}
  }

  handleChange = (event: React.ChangeEvent<HTMLSelectElement>)=>{
    this.setState({value:event.target.value});
  }

  handleSubmit = (event: React.FormEvent<HTMLFormElement>)=>{
    event.preventDefault();
    this.setState({submitted:this.state.value});
  }
  
  render() { 
    return <div>
    <form className="form-inline" onSubmit={this.handleSubmit}>
      <div className="form-group">
        <label htmlFor="ime">biblioteka:</label>
        <select className="form-control" id="ime" placeholder="ime" value={this.state.value} onChange={this.handleChange}>
          <option value="angular">Angular</option>
          <option value="react">React</option>
          <option value="vue">Vue</option>
        </select>
      </div>
      <button type="submit" className="btn btn-primary">posalji</button>
    </form>
    <h2>submitted: {this.state.submitted}</h2>
    </div>
  }
}


// više input polja
class Primer18 extends React.Component<{},{firstName:string, lastName:string, submitted:string}>{
  constructor(props:{}) {
    super(props);
    this.state = {firstName:'',lastName:'',submitted:''}
  }

  handleChange = (event: React.ChangeEvent<HTMLInputElement>)=>{
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({
      [name]:value
    } as Pick<{firstName:string; lastName:string; submitted:string}, keyof {firstName: string; lastName: string}>);
  }

  handleSubmit = (event: React.FormEvent<HTMLFormElement>)=>{
    event.preventDefault();
    this.setState({submitted:this.state.firstName+' '+this.state.lastName});
  }

  render() {
    return <div>
    <form className="form-inline" onSubmit={this.handleSubmit}>
      <div className="form-group">
        <label htmlFor="ime">ime</label>
        <input type="text" className="form-control" id="ime" placeholder="ime" name="firstName" value={this.state.firstName} onChange={this.handleChange}></input>
      </div>
      <div className="form-group">
        <label htmlFor="ime">prezime</label>
        <input type="text" className="form-control" id="prezime" placeholder="prezime" name="lastName" value={this.state.lastName} onChange={this.handleChange}></input>
      </div>
      <button type="submit" className="btn btn-primary">posalji</button>
    </form>
    <h2>submitted: {this.state.submitted}</h2>
    </div>
  }  
}

// HOC
export interface InjectedCounterProps {
  value: number;
  onIncrement(): void;
  onDecrement(): void;
}

interface MakeCounterProps {
  minValue?: number;
  maxValue?: number;
}

interface MakeCounterState {
  value: number;
}

const makeCounter = <P extends InjectedCounterProps>(
  Component: React.ComponentType<P>
) =>
  class MakeCounter extends React.Component<
    Subtract<P, InjectedCounterProps> & MakeCounterProps,
    MakeCounterState
  > {
    state: MakeCounterState = {
      value: 0,
    };

    increment = () => {
      this.setState(prevState => ({
        value:
          prevState.value === this.props.maxValue
            ? prevState.value
            : prevState.value + 1,
      }));
    };

    decrement = () => {
      this.setState(prevState => ({
        value:
          prevState.value === this.props.minValue
            ? prevState.value
            : prevState.value - 1,
      }));
    };

    render() {
      const { minValue, maxValue, ...props } = this.props;
      return (
        <Component
          {...props as P}
          value={this.state.value}
          onIncrement={this.increment}
          onDecrement={this.decrement}
        />
      );
    }
  };

interface CounterProps extends InjectedCounterProps {
  style?: React.CSSProperties;
}

const Counter = (props: CounterProps) => (
  <div style={props.style}>
    <button onClick={props.onDecrement}> - </button>
    {props.value}
    <button onClick={props.onIncrement}> + </button>
  </div>
);

const Primer19 = makeCounter(Counter);

