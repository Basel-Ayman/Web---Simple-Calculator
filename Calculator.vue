<template>
  <div class="calculator">
    <div class="display">{{ current || '0'}}</div>
    <button @click="percent" class="btn operators">%</button>
    <button @click="clear" class="btn operators">C</button>
    <button @click="clear" class="btn operators">CE</button>
    <button @click="back" class="btn operators">⌫</button>
    <button @click="inverse" class="btn operators">1/<sub>x</sub></button>
    <button @click="square" class="btn operators">x<sup>2</sup></button>
    <button @click="squareRoot" class="btn operators">&#x221A;x</button>
    <button @click="divide" class="btn operators">&#xf7;</button>
    <button @click="append('7')" class="btn">7</button>
    <button @click="append('8')" class="btn">8</button>
    <button @click="append('9')" class="btn">9</button>
    <button @click="multiply" class="btn operators">&#xd7;</button>
    <button @click="append('4')" class="btn">4</button>
    <button @click="append('5')" class="btn">5</button>
    <button @click="append('6')" class="btn">6</button>
    <button @click="subtract" class="btn operators">&#x2212;</button>
    <button @click="append('1')" class="btn">1</button>
    <button @click="append('2')" class="btn">2</button>
    <button @click="append('3')" class="btn">3</button>
    <button @click="add" class="btn operators">&#x2b;</button>
    <button @click="sign" class="btn">&#x207A;/<sub>-</sub></button>
    <button @click="append('0')" class="btn">0</button>
    <button @click="dot" class="btn">.</button>
    <button @click="equal" class="equal" style="border: black 1px solid">=</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      previous: null,
      current: '',
      operatorClicked: false,
      operator: '',
      equalClicked: false
    }
  },
  methods: {
    clear () {
      this.current = ''
    },
    back () {
      if (this.equalClicked) {
        return this.current
      } else {
        this.current = this.current.slice(0, -1)
      }
    },
    sign () {
      axios.get('http://localhost:8083/sign', { params: { num1: Number(this.current) } })
        .then(response => {
          this.current = response.data
        })
    },
    percent () {
      axios.get('http://localhost:8083/percent', { params: { num1: Number(this.current) } })
        .then(response => {
          this.current = response.data
        })
      this.operatorClicked = true
    },
    append (number) {
      if (this.equalClicked || this.operatorClicked) {
        this.current = ''
        this.equalClicked = false
        this.operatorClicked = false
      } else if (this.current === '0') {
        this.current = ''
      }
      this.current = this.current + number
    },
    dot () {
      if (this.current.indexOf('.') === -1) {
        this.append('.')
      }
    },
    divide () {
      this.operator = '/'
      this.previous = this.current
      this.current = '0'
      this.operatorClicked = true
    },
    multiply () {
      this.operator = '*'
      this.previous = this.current
      this.current = '0'
      this.operatorClicked = true
    },
    subtract () {
      this.operator = '-'
      this.previous = this.current
      this.current = '0'
      this.operatorClicked = true
    },
    add () {
      this.operator = '+'
      this.previous = this.current
      this.current = '0'
      this.operatorClicked = true
    },
    equal () {
      if (this.operator === '/') {
        axios.get('http://localhost:8083/divide', { params: { num1: Number(this.previous), num2: Number(this.current) } })
          .then(response => {
            this.current = response.data
          })
      } else if (this.operator === '*') {
        axios.get('http://localhost:8083/multiply', { params: { num1: Number(this.previous), num2: Number(this.current) } })
          .then(response => {
            this.current = response.data
          })
      } else if (this.operator === '-') {
        axios.get('http://localhost:8083/subtract', { params: { num1: Number(this.previous), num2: Number(this.current) } })
          .then(response => {
            this.current = response.data
          })
      } else if (this.operator === '+') {
        axios.get('http://localhost:8083/add', { params: { num1: Number(this.previous), num2: Number(this.current) } })
          .then(response => {
            this.current = response.data
          })
      }
      this.previous = null
      this.operator = null
      this.equalClicked = true
    },
    inverse () {
      axios.get('http://localhost:8083/inverse', { params: { num1: Number(this.current) } })
        .then(response => {
          this.current = response.data
        })
      this.operatorClicked = true
    },
    square () {
      axios.get('http://localhost:8083/square', { params: { num1: Number(this.current) } })
        .then(response => {
          this.current = response.data
        })
      this.operatorClicked = true
    },
    squareRoot () {
      axios.get('http://localhost:8083/squareRoot', { params: { num1: Number(this.current) } })
        .then(response => {
          this.current = response.data
        })
      this.operatorClicked = true
    }
  }
}
</script>

<style scoped>
.equal {
  background-color: #3399ff;
}
.btn {
  background-color: #ffffff;
  border: black 1px solid;
}
.btn:hover {
  background-color: #353535;
  cursor: pointer;
}
.operators {
  background-color: #e0ebeb;
}
.display {
  grid-column: 1 / 5;
  background-color: #d1e0e0;
}
.calculator {
  margin: 0 auto;
  width: 400px;
  font-size: 40px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: minmax(50px, auto);
}
</style>
