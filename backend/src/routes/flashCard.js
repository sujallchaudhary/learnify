const express = require('express');
const router = express.Router();
const {createFlashCard,getFlashCards,getAllFlashCards} = require('../controllers/flashCardController');

router.post('/',createFlashCard);
router.get('/:id',getFlashCards);
router.get('/',getAllFlashCards);

module.exports = router;
