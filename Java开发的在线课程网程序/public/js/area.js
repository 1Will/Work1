//定义地区对象
function data( ){
    this.Items = {}
}

//返回对象类型原型的引用
data.prototype.add = function( id,iArray ){
    this.Items[id] = iArray;
}
//检测是否存在地区数组
data.prototype.existArray = function( id ){
    if( typeof( this.Items[id] ) == "undefined" ) {return false;}
    return true;
}

//v=1省,v=2市，v=3县
function change(v,s)
{
    var str="0";
    for( i=0;i<v;i++ ){
          str+=( "_"+( document.getElementById( s[i] ).selectedIndex-1 ) );
    }

    var ss=document.getElementById( s[v] );
	if(ss!=null){
		with( ss ){
			length = 0;
			options[0]=new Option( opt0[v],opt0[v] );
			if( v && document.getElementById( s[v-1] ).selectedIndex>0 || !v ) {
				if( data.existArray( str ) ){
					ar = data.Items[str];
					for( i=0;i<ar.length;i++ )
						options[length]=new Option( ar[i],ar[i] );
					if( v )
					options[1].selected = true;
				}
			}
			//递归调用
			if(++v<s.length ){
				change( v,s);
			}
		}
	}
}

var data = new data( );
//data.add( "0",["北京","上海"] );
//data.add( "0_0",["东城区","西城区","崇文区"] );
//
//data.add( "0_1",["黄浦区"] );
var opt0 = ["省份","地级市","市、县级市、县"];
