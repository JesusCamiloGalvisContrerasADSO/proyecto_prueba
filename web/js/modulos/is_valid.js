
const is_valid = (event, form) =>{
  // event.preventDefault()
  const elements = document.querySelectorAll(form)
  let bandera = true
  elements.forEach(element => {
    if (element.value == "" || element.value == 0) {
      console.log(element);
      element.classList.add("error")
      bandera = false;
    }
    
  });
  console.log(bandera)
  return bandera
  
}

export default is_valid;