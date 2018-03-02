const corenlp = require("corenlp")
const stream = require('stream')

module.exports = {

	analyze : function(data){
	    console.log("Les verbes de la phrase sont : ")
	    const CoreNLP = corenlp.default
	    const connector = new corenlp.ConnectorServer({
	        dsn: 'http://127.0.0.1:9000',
	    });
	    const props = new corenlp.Properties()
	    const pipeline = new corenlp.Pipeline(props, 'English')
	    props.setProperty('annotators', 'tokenize,ssplit,pos,lemma,ner,parse')
	   
	    const sent = new CoreNLP.simple.Sentence(data)
	    pipeline.annotate(sent)
	      .then(sent => {
	        const tree = CoreNLP.util.Tree.fromSentence(sent);
	        tree.visitLeaves(node => {
	/*          if(node.pos() === 'V'){
	            console.log(node.word());
	          }*/
	            console.log(`mot : ${node.word()}`);
	            console.log(`pos : ${node.pos()}`);
	            console.log(`ner : ${node.token().ner()}`);
	        })
	      })
	      .catch(err => {
	        console.log('err', err);
	      }); 	
	}

}