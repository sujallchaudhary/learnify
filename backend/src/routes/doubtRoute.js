const express = require('express');
const router = express.Router();
const {solveDoubt,getAllDoubts,getDoubt,markDoubtAsSolved} = require('../controllers/doubtController');
const {fileUpload} = require('../middleware/fileUpload');

router.post('/new',fileUpload,solveDoubt);
router.get('/all',getAllDoubts);
router.get('/:id',getDoubt);
router.put('/solved/:id',markDoubtAsSolved);

module.exports = router;
