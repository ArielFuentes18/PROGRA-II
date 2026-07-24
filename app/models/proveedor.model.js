module.exports = (sequelize, Sequelize) => {

    const Proveedor = sequelize.define("proveedor",{
nombre: {
    type: Sequelize.STRING
},
apellido: {
    type: Sequelize.STRING
},
direccion: {
    type: Sequelize.STRING
},
correo: {
    type: Sequelize.STRING
},
telefono: {
    type: Sequelize.STRING
},
clase: {
    type: Sequelize.STRING
},
status:{
    type:Sequelize.BOOLEAN
}

    } );
    return Proveedor;
};