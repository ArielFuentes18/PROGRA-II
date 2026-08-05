module.exports = (sequelize, Sequelize) => {

    const Producto = sequelize.define("producto",{
nombre: {
    type: Sequelize.STRING
},
descripcion: {
    type: Sequelize.STRING
},
precio: {
    type: Sequelize.DECIMAL(10, 2)
},
stock: {
    type: Sequelize.INTEGER
},

status:{
    type:Sequelize.BOOLEAN
}
    } );
    return Producto;
};



