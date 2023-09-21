package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Paciente> pacientes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar paciente");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (escolha == 1) {
                System.out.print("Nome do paciente: ");
                String nome = scanner.nextLine();
                System.out.print("Idade do paciente: ");
                int idade = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                System.out.print("Endereço do paciente: ");
                String endereco = scanner.nextLine();

                Paciente paciente = new Paciente(nome, idade, endereco);
                pacientes.add(paciente);

                // Gerar PDF
                String nomeArquivo = "paciente_" + paciente.getNome() + ".pdf";
                gerarPDF(paciente, nomeArquivo);

                System.out.println("Paciente cadastrado com sucesso!");
                abrirPDF(nomeArquivo);
                System.out.println("pdf deletado");
            } else if (escolha == 2) {
                break;
            } else {
                System.out.println("Opção inválida!");

            }
        }
    }
    private static void gerarPDF(Paciente paciente, String nomeArquivo) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            document.open();
            document.add(new Paragraph("Informações do Paciente"));
            document.add(new Paragraph("Nome: " + paciente.getNome()));
            document.add(new Paragraph("Idade: " + paciente.getIdade()));
            document.add(new Paragraph("Endereço: " + paciente.getEndereco()));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void deletarPDF(String nomeArquivo) {
        File pdfFile = new File(nomeArquivo);
        if (pdfFile.exists()) {
            if (pdfFile.delete()) {
                System.out.println("O arquivo PDF foi excluído com sucesso.");
            } else {
                System.out.println("Não foi possível excluir o arquivo PDF.");
            }
        } else {
            System.out.println("O arquivo PDF não foi encontrado.");
        }
    }
    private static void abrirPDF(String nomeArquivo) {
        try {
            File pdfFile = new File(nomeArquivo);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("O arquivo PDF não foi encontrado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}