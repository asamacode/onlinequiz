package com.asama.onlinequiz.lecturer.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asama.onlinequiz.dto.QuestionDTO;
import com.asama.onlinequiz.model.Answer;
import com.asama.onlinequiz.model.Lecturer;
import com.asama.onlinequiz.model.Question;
import com.asama.onlinequiz.model.Subject;
import com.asama.onlinequiz.service.AnswerService;
import com.asama.onlinequiz.service.QuestionService;
import com.asama.onlinequiz.service.SubjectService;

@Controller
@RequestMapping("/lecturer/question")
public class QuestionLecturerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    QuestionService questionService;

    @Autowired
    HttpSession session;

    @Autowired
    HttpServletRequest request;

    @PostMapping("/import")
    public String mapReapExcelDatatoDB(RedirectAttributes re, @RequestParam("file") MultipartFile reapExcelDataFile,
            @RequestParam("subId") Long subId) throws IOException {

        Lecturer lecturer = (Lecturer) session.getAttribute("user");
        Optional<Subject> subject = subjectService.findById(subId);
        if (subject.isPresent()) {

            Workbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
            org.apache.poi.ss.usermodel.Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            try {
                while (iterator.hasNext()) {
                    Question question = new Question();
                    question.setLecturer(lecturer);
                    question.setSubject(subject.get());

                    /*get all row*/
                    Row currentRow = iterator.next();

                    /*get all cell per row*/
                    Iterator<Cell> cellIterator = currentRow.cellIterator();

                    /*get question level*/
                    Cell cellLevel = cellIterator.next();
                    question.setLevel((int) cellLevel.getNumericCellValue());

                    /* get question content */
                    Cell cellQuestion = cellIterator.next();
                    question.setContent(cellQuestion + "");

                    Question qs = questionService.save(question);

                    while (cellIterator.hasNext()) {
                        Answer answer = new Answer();
                        Cell nextCell = cellIterator.next();

                        Workbook book = currentRow.getSheet().getWorkbook();
                        CellStyle style = nextCell.getCellStyle();
                        short fontIndex = style.getFontIndex();
                        Font font = book.getFontAt(fontIndex);

                        /*them noi dung*/
                        answer.setContent(nextCell + "");
                        answer.setQuestion(qs);

                        if (font.getBold()) {
                            Answer rightAnswer = answerService.save(answer);
                            question.setRightAnswer(rightAnswer);
                            questionService.update(question);
                        } else {
                            answerService.save(answer);
                        }
                    }

                }
            } catch (Exception e) {
                re.addAttribute("message", "Xảy ra lỗi !");
            } finally {
                workbook.close();
            }

            re.addAttribute("message", "Thêm thành công !");

        } else {
            re.addAttribute("message", "Môn học không tồn tại !");
        }

        return "redirect:/lecturer/question/list";

    }

    @GetMapping("/import")
    public String importPage(Model model) {

        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects", subjects);

        return "lecturer/question/import";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Optional<Question> question = questionService.findById(id);

        if (question.isPresent()) {
            List<Answer> answers = answerService.findAllByQuestionId(question.get().getId());
            List<Subject> subjects = subjectService.findAll();
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.get().getId());
            questionDTO.setAnswer1(answers.get(0).getContent());
            questionDTO.setAnswer2(answers.get(1).getContent());
            questionDTO.setAnswer3(answers.get(2).getContent());
            questionDTO.setAnswer4(answers.get(3).getContent());
            questionDTO.setRightAnswer(question.get().getRightAnswer().getContent());
            questionDTO.setContent(question.get().getContent());
            questionDTO.setImage(question.get().getImage());
            questionDTO.setLecturer(question.get().getLecturer());
            questionDTO.setLevel(question.get().getLevel());
            questionDTO.setSubject(question.get().getSubject());

            model.addAttribute("subjects", subjects);
            model.addAttribute("question", questionDTO);
        } else {
            model.addAttribute("question", new Question());
            model.addAttribute("message", "Câu hỏi không tồn tại !");
        }

        return "lecturer/question/add";
    }

    @PostMapping("/update")
    public String update(RedirectAttributes re, Model model, @Valid @ModelAttribute("question") QuestionDTO questionDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            List<Subject> subjects = subjectService.findAll();
            model.addAttribute("subjects", subjects);
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "lecturer/question/add";
        }
        String correctAns = request.getParameter(questionDTO.getRightAnswer());

        Lecturer lecturer = (Lecturer) session.getAttribute("user");
        Question question = new Question();
        question.setContent(questionDTO.getContent());
        question.setImage(questionDTO.getImage());
        question.setLevel(questionDTO.getLevel());
        question.setSubject(questionDTO.getSubject());
        question.setLecturer(lecturer);

        Question qs = questionService.update(question);

        Set<Answer> answers = new HashSet<Answer>();
        answers.add(new Answer(questionDTO.getAnswer1(), qs));
        answers.add(new Answer(questionDTO.getAnswer2(), qs));
        answers.add(new Answer(questionDTO.getAnswer3(), qs));
        answers.add(new Answer(questionDTO.getAnswer4(), qs));
        answerService.saveAll(answers);
        for (Answer as : answers) {
            if (as.getContent().equals(correctAns)) {
                qs.setRightAnswer(as);
                questionService.update(qs);
            }
        }

        re.addAttribute("message", "Cập nhật thành công !");
        return "redirect:/lecturer/question/update/" + question.getId();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(RedirectAttributes re, @PathVariable("id") Long id) {
        Optional<Question> question = questionService.findById(id);
        if (question.isPresent()) {
            questionService.delete(question.get());
            return "Xóa thành công";
        } else {
            return "Câu hỏi không tồn tại !";
        }
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Lecturer lecturer = (Lecturer) session.getAttribute("user");
        List<Question> list = questionService.findAllByLecturerId(lecturer.getId());
        model.addAttribute("questions", list);
        return "lecturer/question/list";
    }

    @GetMapping("/add")
    public String add(Model model) {

        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects", subjects);
        model.addAttribute("question", new QuestionDTO());
        return "lecturer/question/add";
    }

    @PostMapping("/add")
    public String addOrUpdate(RedirectAttributes re, Model model,
            @Valid @ModelAttribute("question") QuestionDTO questionDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<Subject> subjects = subjectService.findAll();
            model.addAttribute("subjects", subjects);
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "lecturer/question/add";
        }

        String correctAns = request.getParameter(questionDTO.getRightAnswer());

        Lecturer lecturer = (Lecturer) session.getAttribute("user");
        Question question = new Question();
        question.setContent(questionDTO.getContent());
        question.setImage(questionDTO.getImage());
        question.setLevel(questionDTO.getLevel());
        question.setSubject(questionDTO.getSubject());
        question.setLecturer(lecturer);

        Question qs = questionService.save(question);

        Set<Answer> answers = new HashSet<Answer>();
        answers.add(new Answer(questionDTO.getAnswer1(), qs));
        answers.add(new Answer(questionDTO.getAnswer2(), qs));
        answers.add(new Answer(questionDTO.getAnswer3(), qs));
        answers.add(new Answer(questionDTO.getAnswer4(), qs));
        answerService.saveAll(answers);
        for (Answer as : answers) {
            if (as.getContent().equals(correctAns)) {
                qs.setRightAnswer(as);
                questionService.update(qs);
            }
        }

        re.addAttribute("message", "Thêm thành công !");
        return "redirect:/lecturer/question/add";
    }
}
